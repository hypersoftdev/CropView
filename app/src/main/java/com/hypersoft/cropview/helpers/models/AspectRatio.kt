package com.hypersoft.cropview.helpers.models

import androidx.annotation.DrawableRes
import com.hypersoft.crop.enums.AspectRatioType

/**
 *   Developer: Sohaib Ahmed
 *   Date: 9/27/2024
 *   Profile:
 *     -> github.com/epegasus
 *     -> linkedin.com/in/epegasus
 */

data class AspectRatio(
    val id: Int,
    val text: String,
    @DrawableRes val iconResId: Int,
    val aspectRatioType: AspectRatioType,
    var isSelected: Boolean = false
)