package lib.data

enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
}

val Direction.opposite: Direction
    get() = when (this) {
        Direction.NORTH -> Direction.SOUTH
        Direction.EAST -> Direction.WEST
        Direction.SOUTH -> Direction.NORTH
        Direction.WEST -> Direction.EAST
    }