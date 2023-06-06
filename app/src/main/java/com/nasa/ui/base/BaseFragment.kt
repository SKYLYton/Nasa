package com.nasa.ui.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import androidx.viewbinding.ViewBinding
import com.nasa.ui.activity.MainActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * @author Fedotov Yakov
 */
private typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<Binding : ViewBinding>(private val inflate: Inflate<Binding>) :
    Fragment() {

    private var binding: Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = inflate.invoke(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    protected fun navigateSafety(destination: NavDirections) =
        findNavController().currentDestination?.getAction(destination.actionId)?.let {
            findNavController().navigate(destination)
        }

    protected fun <T> subscribe(flow: Flow<T>, action: suspend (T) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    flow.collect { action.invoke(it) }
                }
            }
        }
    }

    protected fun runBinding(block: Binding.() -> Unit) {
        binding?.let(block)
    }

    protected fun addHandleBackCallBack(callback: OnBackPressedCallback) {
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    protected fun addHandleBackCallBackActivity(callback: OnBackPressedCallback) {
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    protected fun onBackPressed() {
        mainActivity?.onBackPressedDispatcher?.onBackPressed()
    }

    protected val mainActivity: MainActivity?
        get() = activity as? MainActivity
}