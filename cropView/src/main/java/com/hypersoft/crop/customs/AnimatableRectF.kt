package com.hypersoft.crop.customs

import android.graphics.RectF

/**
 *   Developer: Sohaib Ahmed
 *   Date: 9/25/2024
 *   Profile:
 *     -> github.com/epegasus
 *     -> linkedin.com/in/epegasus
 */

class AnimatableRectF : RectF() {

    fun setTop(top: Float) {
        this.top = top
    }

    fun setBottom(bottom: Float) {
        this.bottom = bottom
    }

    fun setRight(right: Float) {
        this.right = right
    }

    fun setLeft(left: Float) {
        this.left = left
    }
}