package com.nasa.ui.fragment.aboutapp

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.nasa.BuildConfig
import com.nasa.R
import com.nasa.databinding.FragmentAboutAppBinding
import com.nasa.ui.base.BaseFragment

/**
 * @author Fedotov Yakov
 */
class AboutAppFragment : BaseFragment<FragmentAboutAppBinding>(FragmentAboutAppBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        runBinding {
            version.text = getString(R.string.about_app_fragment_version, BuildConfig.VERSION_NAME)

            appBar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}