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