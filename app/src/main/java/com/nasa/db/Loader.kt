package com.nasa.db

import android.content.Context
import com.nasa.BuildConfig
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Fedotov Yakov
 */
@Singleton
class Loader @Inject constructor(private val context: Context) {

    /*
    * Первый ли показ экрана карт с момента установки приложения
    */
    var isBlackTheme: Boolean
        set(value) = context.getSharedPreferences(APP, Context.MODE_PRIVATE).edit()
            .putBoolean(BLACK_MODE_THEME, value).apply()
        get() = context.getSharedPreferences(APP, Context.MODE_PRIVATE)
            .getBoolean(BLACK_MODE_THEME, false)

    companion object {
        /*
        * Категории данных
        */
        private const val APP = BuildConfig.APPLICATION_ID

        /*
        * Наименование полей
        */
        private const val BLACK_MODE_THEME = "BLACK_MODE_THEME"

    }
}