package lib.data

class Matrix<T>(elements: Collection<Collection<T>>) {
    private val matrix = elements.map { it.toMutableList() }
        .toMutableList()

    val rows: Int = matrix.size
    val cols: Int = matrix[0].size

    val dimension: Vec2
        get() = Vec2(cols, rows)

    val rowIndices: IntRange
        get() = 0 until rows

    val colIndices: IntRange
        get() = 0 until cols

    operator fun contains(pos: Vec2): Boolean {
        return pos.y in rowIndices && pos.x in colIndices
    }

    operator fun get(pos: Vec2): T {
        return matrix[pos.y][pos.x]
    }

    operator fun set(pos: Vec2, value: T) {
        matrix[pos.y][pos.x] = value
    }

    fun row(row: Int): Sequence<T> {
        return matrix[row].asSequence()
    }

    fun col(col: Int): Sequence<T> {
        return rowIndices.asSequence()
            .map { matrix[it][col] }
    }
}