package dev.pegasus.crop.util.model

/**
 *   Developer: Sohaib Ahmed
 *   Date: 9/25/2024
 *   Profile:
 *     -> github.com/epegasus
 *     -> linkedin.com/in/epegasus
 */

enum class Edge {
    NONE,
    LEFT,
    TOP,
    RIGHT,
    BOTTOM
}

fun Edge.opposite() {
    when (this) {
        Edge.LEFT -> Edge.RIGHT
        Edge.TOP -> Edge.BOTTOM
        Edge.RIGHT -> Edge.RIGHT
        Edge.BOTTOM -> Edge.TOP
        Edge.NONE -> Edge.NONE
    }
}