package com.hypersoft.crop.enums

/**
 *   Developer: Sohaib Ahmed
 *   Date: 9/30/2024
 *   Profile:
 *     -> github.com/epegasus
 *     -> linkedin.com/in/epegasus
 */

enum class DragState(val value: Int) {
    DRAG_ALL(0),
    DRAG_CORNER(1),
    DRAG_EDGE(2),
    DRAG_BITMAP(3),
    DRAG_CORNER_EDGE(4);

    companion object {
        fun fromValue(value: Int): DragState {
            return entries.firstOrNull { it.value == value } ?: DRAG_ALL
        }
    }
}