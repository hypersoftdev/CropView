package com.hypersoft.crop.util.extensions

import android.content.res.Resources
import android.util.TypedValue

/**
 *   Developer: Sohaib Ahmed
 *   Date: 9/30/2024
 *   Profile:
 *     -> github.com/epegasus
 *     -> linkedin.com/in/epegasus
 */

// Convert dp to px
fun Float.toPx(resources: Resources): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, resources.displayMetrics)
}

// Convert px to dp
fun Float.toDp(resources: Resources): Float {
    return this / (resources.displayMetrics.densityDpi.toFloat() / 160f)
}