package year2022

object Day17 {
    private val rocks = listOf(
        listOf(0 to 0, 1 to 0, 2 to 0, 3 to 0),
        listOf(1 to 2, 0 to 1, 1 to 1, 2 to 1, 1 to 0),
        listOf(2 to 2, 2 to 1, 0 to 0, 1 to 0, 2 to 0),
        listOf(0 to 3, 0 to 2, 0 to 1, 0 to 0),
        listOf(0 to 1, 1 to 1, 0 to 0, 1 to 0)
    )

    fun solve1(input: List<String>): Int {
        val jets = input[0].toList()

        var maxY = -1
        var rockIndex = 0
        var jetIndex = 0
        val staticRocks = mutableSetOf<Pair<Int, Int>>()

        repeat(2022) {
            var x = 2
            var y = maxY + 4
            val rock = rocks[rockIndex++ % rocks.size]
            var moveDown = false

            while (true) {
                var nextX = x
                var nextY = y

                if (moveDown) {
                    nextY--

                    if (collides(rock, nextX, nextY, staticRocks)) {
                        rock.forEach { (pieceX, pieceY) ->
                            maxY = maxOf(maxY, y + pieceY)
                            staticRocks += (x + pieceX) to (y + pieceY)
                        }

                        break
                    }
                } else {
                    if (jets[jetIndex++ % jets.size] == '>') {
                        nextX++
                    } else {
                        nextX--
                    }

                    if (collides(rock, nextX, nextY, staticRocks)) {
                        nextX = x
                    }
                }

                x = nextX
                y = nextY
                moveDown = !moveDown
            }
        }

        return maxY + 1
    }

    fun solve2(input: List<String>): Long {
        val targetRockCount = 1_000_000_000_000L

        val steps = mutableMapOf<Pair<Int, Int>, MutableList<Step>>()
        val heights = mutableListOf<Int>()

        var finalHeight = 0L

        simulate(input) { rockCount, jetCount, height ->
            val rockIndex = rockCount % rocks.size
            val jetIndex = jetCount % input[0].length

            val lastStep = steps[rockIndex to jetIndex].orEmpty()
            if (lastStep.size == 2 && (lastStep[1].height - lastStep[0].height) == (height - lastStep[1].height)) {
                val offsetCount = lastStep[1].rockCount
                val offsetHeight = lastStep[1].height

                val repeatCycle = rockCount - lastStep[1].rockCount
                val repeatHeight = height - lastStep[1].height

                val mod = (targetRockCount - offsetCount) % repeatCycle

                finalHeight = repeatHeight * (targetRockCount - offsetCount - mod) / repeatCycle + offsetHeight +
                        (heights[(offsetCount + mod).toInt()] - offsetHeight)

                return@simulate false
            }

            steps.getOrPut(rockIndex to jetIndex) { mutableListOf() } += Step(
                rockCount = rockCount,
                height = height
            )
            heights += height

            true
        }

        return finalHeight
    }

    private fun simulate(
        input: List<String>,
        observer: (rockCount: Int, jetCount: Int, height: Int) -> Boolean
    ) {
        val jets = input[0].toList()

        var maxY = -1
        var rockCount = 0
        var jetCount = 0
        val staticRocks = mutableSetOf<Pair<Int, Int>>()

        while (observer(rockCount, jetCount, maxY + 1)) {
            var x = 2
            var y = maxY + 4
            val rock = rocks[rockCount++ % rocks.size]
            var moveDown = false

            while (true) {
                var nextX = x
                var nextY = y

                if (moveDown) {
                    nextY--

                    if (collides(rock, nextX, nextY, staticRocks)) {
                        rock.forEach { (pieceX, pieceY) ->
                            maxY = maxOf(maxY, y + pieceY)
                            staticRocks += (x + pieceX) to (y + pieceY)
                        }

                        break
                    }
                } else {
                    if (jets[jetCount++ % jets.size] == '>') {
                        nextX++
                    } else {
                        nextX--
                    }

                    if (collides(rock, nextX, nextY, staticRocks)) {
                        nextX = x
                    }
                }

                x = nextX
                y = nextY
                moveDown = !moveDown
            }
        }
    }

    private fun collides(
        rock: List<Pair<Int, Int>>,
        xPos: Int,
        yPos: Int,
        staticRocks: Set<Pair<Int, Int>>
    ): Boolean {

        rock.forEach { (pieceX, pieceY) ->
            val x = pieceX + xPos
            val y = pieceY + yPos

            if (x < 0 || x > 6) return true
            if (x to y in staticRocks) return true
            if (y < 0) return true
        }

        return false
    }

    private data class Step(
        val rockCount: Int,
        val height: Int
    )
}