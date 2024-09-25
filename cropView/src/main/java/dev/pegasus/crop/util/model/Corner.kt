package dev.pegasus.crop.util.model

/**
 *   Developer: Sohaib Ahmed
 *   Date: 9/25/2024
 *   Profile:
 *     -> github.com/epegasus
 *     -> linkedin.com/in/epegasus
 */

enum class Corner {
    NONE,
    TOP_RIGHT,
    TOP_LEFT,
    BOTTOM_RIGHT,
    BOTTOM_LEFT
}

fun Corner.opposite() {
    when (this) {
        Corner.TOP_RIGHT -> Corner.BOTTOM_LEFT
        Corner.TOP_LEFT -> Corner.BOTTOM_RIGHT
        Corner.BOTTOM_RIGHT -> Corner.TOP_LEFT
        Corner.BOTTOM_LEFT -> Corner.TOP_RIGHT
        Corner.NONE -> Corner.NONE
    }
}