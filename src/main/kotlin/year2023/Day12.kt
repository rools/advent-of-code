package year2023

object Day12 {
    fun solve1(input: List<String>): Long {
        return calculateSumOfArrangements(input, folds = 1)
    }

    fun solve2(input: List<String>): Long {
        return calculateSumOfArrangements(input, folds = 5)
    }

    private fun calculateSumOfArrangements(input: List<String>, folds: Int): Long {
        return input.sumOf { line ->
            val parts = line.split(' ')
            val arrangement = parts[0].toCharArray()
                .toList()
                .unfolded(folds, separator = '?')
                .toMutableList()
            val sizes = parts[1].split(',')
                .map { it.toInt() }
                .unfolded(folds)
            val minSpace = sizes.sum() + sizes.size - 1
            calculateArrangements(arrangement, sizes, minSpace, 0, 0, 0, mutableMapOf())
        }
    }

    private fun <T> List<T>.unfolded(folds: Int, separator: T? = null): List<T> = buildList {
        repeat(folds) { index ->
            if (index > 0 && separator != null) add(separator)
            addAll(this@unfolded)
        }
    }

    private fun calculateArrangements(
        arrangement: MutableList<Char>,
        sizes: List<Int>,
        spaceLeft: Int,
        index: Int,
        sizeIndex: Int,
        currentSize: Int,
        cache: MutableMap<Int, Long>
    ): Long {
        if (currentSize > 0 && sizes[sizeIndex] < currentSize) {
            return 0
        }

        if (index > arrangement.lastIndex) {
            return 1
        }

        if (arrangement[index] == '?') {
            arrangement[index] = '#'
            var validCount = calculateArrangements(arrangement, sizes, spaceLeft, index, sizeIndex, currentSize, cache)
            arrangement[index] = '.'
            validCount += calculateArrangements(arrangement, sizes, spaceLeft, index, sizeIndex, currentSize, cache)
            arrangement[index] = '?'
            return validCount
        }

        if (arrangement[index] == '#') {
            if (sizeIndex >= sizes.size) return 0
            return calculateArrangements(
                arrangement = arrangement,
                sizes = sizes,
                spaceLeft = spaceLeft - 1,
                index = index + 1,
                sizeIndex = sizeIndex,
                currentSize = currentSize + 1,
                cache
            )
        }

        if (currentSize > 0) {
            if (sizes[sizeIndex] != currentSize) return 0
            return cache.getOrPut((sizeIndex + 1) * 1000 + (index + 1)) {
                calculateArrangements(
                    arrangement = arrangement,
                    sizes = sizes,
                    spaceLeft = spaceLeft - 1,
                    index = index + 1,
                    sizeIndex = sizeIndex + 1,
                    currentSize = 0,
                    cache
                )
            }
        }

        if (spaceLeft >= (arrangement.size - index)) {
            return 0
        }

        return calculateArrangements(
            arrangement = arrangement,
            sizes = sizes,
            spaceLeft = spaceLeft,
            index = index + 1,
            sizeIndex = sizeIndex,
            currentSize = currentSize,
            cache = cache
        )
    }
}