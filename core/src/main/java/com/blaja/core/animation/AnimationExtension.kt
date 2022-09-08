package com.blaja.core.animation

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator

const val FADE_DURATION_DEFAULT_IN_MILLISECONDS = 500L


fun View.fadeIn(duration: Long = FADE_DURATION_DEFAULT_IN_MILLISECONDS, animationLifeCycleListener: AnimationLifeCycleListener? = null) {
    val fadeIn = AlphaAnimation(0f, 1f)
    fadeIn.interpolator = DecelerateInterpolator()
    fadeIn.duration = duration
    fadeIn.onAnimationEnd {
        visibility = View.VISIBLE
        animationLifeCycleListener?.onAnimationEnd()
    }

    startAnimation(fadeIn)
    animationLifeCycleListener?.onAnimationStart()
}

fun Animation.onAnimationEnd(onAnimationEnd: () -> Unit) {
    this.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {}

        override fun onAnimationStart(animation: Animation?) {}

        override fun onAnimationEnd(animation: Animation?) {
            onAnimationEnd.invoke()
        }
    })
}

fun View.animateHighlight(onAnimationEndCallback: () -> Unit) {
    fadeIn(animationLifeCycleListener = object : AnimationLifeCycleListener {
        override fun onAnimationStart() {
        }

        override fun onAnimationEnd() {
            onAnimationEndCallback()
        }
    })
}