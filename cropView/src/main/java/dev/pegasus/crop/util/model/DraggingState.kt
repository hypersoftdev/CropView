package dev.pegasus.crop.util.model

/**
 *   Developer: Sohaib Ahmed
 *   Date: 9/25/2024
 *   Profile:
 *     -> github.com/epegasus
 *     -> linkedin.com/in/epegasus
 */


sealed class DraggingState {
    data class DraggingCorner(var corner: Corner) : DraggingState()

    data class DraggingEdge(var edge: Edge) : DraggingState()

    object DraggingBitmap : DraggingState()

    object Idle : DraggingState()
}