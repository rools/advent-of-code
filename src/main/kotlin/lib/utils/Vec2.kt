package lib.utils

import lib.data.Vec2

operator fun Vec2.rem(vec: Vec2): Vec2 {
    return Vec2(x % vec.x, y % vec.y)
}