package com.nasa.ui.fragment.menu

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.jakewharton.processphoenix.ProcessPhoenix
import com.nasa.databinding.FragmentMenuBinding
import com.nasa.ui.activity.MainActivity
import com.nasa.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Fedotov Yakov
 */
@AndroidEntryPoint
class MenuFragment : BaseFragment<FragmentMenuBinding>(FragmentMenuBinding::inflate) {

    private val viewModel: MenuViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        runBinding {

            aboutApp.setOnClickListener {
                navigateSafety(MenuFragmentDirections.actionMenuFragmentToAboutAppFragment())
            }

            settings.setOnClickListener {
                navigateSafety(MenuFragmentDirections.actionMenuFragmentToSettingsFragment())
            }

        }
    }

    private fun restartNavigation() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        ProcessPhoenix.triggerRebirth(requireContext(), intent)
    }
}