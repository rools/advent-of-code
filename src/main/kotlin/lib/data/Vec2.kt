package lib.data

import kotlin.math.absoluteValue

data class Vec2(
    val x: Int,
    val y: Int
)

operator fun Vec2.plus(vec: Vec2): Vec2 {
    return Vec2(x + vec.x, y + vec.y)
}

fun Vec2.distanceTo(vec: Vec2): Int {
    return (x - vec.x).absoluteValue + (y - vec.y).absoluteValue
}

operator fun Vec2.plus(direction: Direction): Vec2 {
    return when (direction) {
        Direction.NORTH -> copy(y = y - 1)
        Direction.EAST -> copy(x = x + 1)
        Direction.SOUTH -> copy(y = y + 1)
        Direction.WEST -> copy(x = x - 1)
    }
}