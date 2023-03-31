package com.nasa.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.nasa.R
import com.nasa.databinding.FragmentHomeBinding
import com.nasa.extension.fadeIn
import com.nasa.extension.fadeOut
import com.nasa.model.ImageModel
import com.nasa.ui.base.BaseFragment
import com.nasa.ui.fragment.home.adapter.ImagesAdapter
import com.nasa.ui.state.BaseState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var adapter: ImagesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ImagesAdapter(requireContext())
        runBinding {
            hd.setColorFilter(ContextCompat.getColor(requireContext(), R.color.secondary), android.graphics.PorterDuff.Mode.MULTIPLY);

            hd.setOnClickListener {
                hd.fadeOut {
                    adapter.isHd = !adapter.isHd
                    if (adapter.isHd) {
                        hd.setColorFilter(ContextCompat.getColor(requireContext(), R.color.primary), android.graphics.PorterDuff.Mode.MULTIPLY)
                    } else {
                        hd.setColorFilter(ContextCompat.getColor(requireContext(), R.color.secondary), android.graphics.PorterDuff.Mode.MULTIPLY)
                    }
                    hd.fadeIn()
                }

            }

            images.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    textName.text = adapter.images[position].title
                    textUrl.text = adapter.images[position].date
                }
            })
            images.adapter = adapter
        }

        viewModel.apply {
            subscribe(imageState, ::handleImageState)
            start()
        }
    }

    private fun handleImageState(state: BaseState<List<ImageModel>>) {
        when(state) {
            is BaseState.Error -> {
                state.throwable
            }
            is BaseState.Loading -> {
            }
            is BaseState.Success -> {
                runBinding {
                    adapter.images = state.item
                }
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.getImage()
    }
}