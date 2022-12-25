package lib.data

data class Rect(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int
)

val Rect.width: Int
    get() = right - left + 1

val Rect.height: Int
    get() = bottom - top + 1

operator fun Rect.contains(vec: Vec2): Boolean {
    return vec.x in left..right && vec.y in top..bottom
}