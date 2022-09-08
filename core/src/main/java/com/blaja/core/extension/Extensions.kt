package com.blaja.core.extension

import android.view.View


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visibleByCondition(isTrue: Boolean) {
    if (isTrue) {
        visible()
    } else {
        gone()
    }
}



