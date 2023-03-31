package com.nasa.extension

import android.os.Vibrator
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation
import androidx.core.content.ContextCompat.getSystemService

/**
 * Расширения для работы с анимацией
 *
 * @author Fedotov Yakov
 */
fun View.fadeIn(durationMillis: Long = 300, onStart: () -> Unit = {}) {
    this.startAnimation(AlphaAnimation(0F, 1F).apply {
        duration = durationMillis
        fillAfter = true
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                onStart()
            }

            override fun onAnimationEnd(animation: Animation?) {}

            override fun onAnimationRepeat(animation: Animation?) {}
        })
    })
}

fun View.fadeOut(durationMillis: Long = 300, onCompletion: () -> Unit = {}) {
    this.startAnimation(AlphaAnimation(1F, 0F).apply {
        duration = durationMillis
        fillAfter = true
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                onCompletion()
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })
    })
}

fun View.translationIn(durationMillis: Long = 300, onStart: () -> Unit = {}) {
    this.startAnimation(TranslateAnimation(0F, 0F, height.toFloat(), 0f).apply {
        duration = durationMillis
        fillAfter = true
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                onStart()
            }

            override fun onAnimationEnd(animation: Animation?) {

            }

            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
    })
}

fun View.translationOut(durationMillis: Long = 300, fillAfter: Boolean = true, onStart: () -> Unit = {}) {
    this.startAnimation(TranslateAnimation(0F, 0F, 0F, height.toFloat()).apply {
        duration = durationMillis
        this.fillAfter = fillAfter
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                onStart()
            }

            override fun onAnimationEnd(animation: Animation?) {}

            override fun onAnimationRepeat(animation: Animation?) {}
        })
    })
}