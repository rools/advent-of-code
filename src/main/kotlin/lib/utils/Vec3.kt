package lib.utils

import lib.data.Vec3

operator fun Vec3.plus(vec: Vec3): Vec3 {
    return Vec3(x + vec.x, y + vec.y, z + vec.z)
}

operator fun Vec3.minus(vec: Vec3): Vec3 {
    return Vec3(x - vec.x, y - vec.y, z - vec.z)
}