package year2022

object Day20 {
    fun solve1(input: List<String>): Long {
        val numbers = input.mapIndexed { index, line ->
            Num(originalIndex = index, number = line.toLong())
        }.toMutableList()

        mix(numbers)

        return getCoordinatesSum(numbers)
    }

    fun solve2(input: List<String>): Long {
        val numbers = input.mapIndexed { index, line ->
            Num(originalIndex = index, number = line.toLong() * 811589153L)
        }.toMutableList()

        repeat(10) {
            mix(numbers)
        }

        return getCoordinatesSum(numbers)
    }

    private fun mix(numbers: MutableList<Num>) {
        repeat(numbers.size) { originalIndex ->
            val currentIndex = numbers.indexOfFirst { it.originalIndex == originalIndex }
            val number = numbers[currentIndex]
            numbers.removeAt(currentIndex)
            val newIndex = ((currentIndex + number.number) % numbers.size + numbers.size) % numbers.size
            numbers.add(newIndex.toInt(), number)
        }
    }

    private fun getCoordinatesSum(numbers: MutableList<Num>): Long {
        val zeroIndex = numbers.indexOfFirst { it.number == 0L }
        return numbers[(zeroIndex + 1000) % numbers.size].number +
                numbers[(zeroIndex + 2000) % numbers.size].number +
                numbers[(zeroIndex + 3000) % numbers.size].number
    }

    private class Num(
        val originalIndex: Int,
        val number: Long
    )
}