package year2023

object Day3 {
    fun solve1(input: List<String>): Int {
        return getNumbers(input)
            .filter { getAdjacentSymbols(input, it).isNotEmpty() }
            .sumOf { it.string.toInt() }
    }

    fun solve2(input: List<String>): Int {
        val partNumbersBySymbol = mutableMapOf<StringWithPos, MutableList<Int>>()

        getNumbers(input).forEach { number ->
            getAdjacentSymbols(input, number).forEach { symbol ->
                partNumbersBySymbol.getOrPut(symbol) { mutableListOf() } += number.string.toInt()
            }
        }

        return partNumbersBySymbol.entries.sumOf { (symbol, partNumbers) ->
            when {
                symbol.string != "*" || partNumbers.size != 2 -> 0
                else -> partNumbers[0] * partNumbers[1]
            }
        }
    }

    private fun getNumbers(input: List<String>): List<StringWithPos> = buildList {
        var number = ""
        repeat(input.size) { y ->
            repeat(input[0].length) { x ->
                val char = input[y][x]
                if (char.isDigit()) {
                    number += char.digitToInt()
                } else {
                    if (number.isNotEmpty()) {
                        add(StringWithPos(number, x - number.length, y))
                    }
                    number = ""
                }
            }
            if (number.isNotEmpty()) {
                add(StringWithPos(number, input[0].length - number.length, y))
                number = ""
            }
        }
    }

    private fun getAdjacentSymbols(
        input: List<String>,
        string: StringWithPos
    ): List<StringWithPos> = buildList {
        (string.x - 1..string.x + string.string.length).forEach { x ->
            (string.y - 1..string.y + 1).forEach { y ->
                if (y !in input.indices || x !in input[y].indices) return@forEach
                if (input[y][x].isDigit() || input[y][x] == '.') return@forEach
                add(StringWithPos(input[y][x].toString(), x, y))
            }
        }
    }

    private data class StringWithPos(
        val string: String,
        val x: Int,
        val y: Int
    )
}