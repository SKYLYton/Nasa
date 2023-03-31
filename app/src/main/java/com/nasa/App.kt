package com.nasa

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.nasa.db.Loader
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * @author Fedotov Yakov
 */
@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var loader: Loader

    override fun onCreate() {
        super.onCreate()
        setDarkModeSettings(loader.isBlackTheme)
    }

    private fun setDarkModeSettings(darkMode: Boolean) {
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}