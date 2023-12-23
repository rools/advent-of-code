package lib.utils

import lib.data.Matrix
import lib.data.Vec2

fun List<String>.toMatrix(): Matrix<Char> {
    return Matrix(map { it.toCharArray().toList() })
}

fun <T> Matrix<T>.positionOf(element: T): Vec2 {
    repeat(rows) { y ->
        repeat(cols) { x ->
            val position = Vec2(x, y)
            if (get(position) == element) return position
        }
    }
    throw NoSuchElementException()
}