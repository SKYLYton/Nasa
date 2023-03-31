package com.nasa.ui.fragment.settings

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.nasa.databinding.FragmentSettingsBinding
import com.nasa.db.Loader
import com.nasa.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    @Inject
    lateinit var loader: Loader

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        runBinding {
            theme.setCheckedWithoutListener(loader.isBlackTheme)
            theme.setOnCheckedChangeListener { buttonView, isChecked ->
                loader.isBlackTheme = isChecked
                setDarkModeSettings(isChecked)
            }

            appBar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setDarkModeSettings(darkMode: Boolean) {
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

}