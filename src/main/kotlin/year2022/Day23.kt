package year2022

import lib.data.Vec2

object Day23 {
    fun solve1(input: List<String>): Int {
        val elves = parseElves(input)

        repeat(10) { round ->
            step(round, elves)
        }

        val width = elves.maxOf { it.x } - elves.minOf { it.x } + 1
        val height = elves.maxOf { it.y } - elves.minOf { it.y } + 1

        return width * height - elves.size
    }

    fun solve2(input: List<String>): Int {
        val elves = parseElves(input)
        var round = 0

        while (step(round, elves)) {
            round++
        }

        return round + 1
    }

    private fun parseElves(input: List<String>): MutableSet<Vec2> {
        val elves = mutableSetOf<Vec2>()

        input.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                if (c == '#') {
                    elves += Vec2(x, y)
                }
            }
        }

        return elves
    }

    private fun step(round: Int, elves: MutableSet<Vec2>): Boolean {
        val proposals = mutableMapOf<Vec2, Vec2>()

        elves.forEach { elf ->
            val isAlone = (-1..1).all { y ->
                (-1..1).none { x ->
                    (x != 0 || y != 0) && Vec2(elf.x + x, elf.y + y) in elves
                }
            }

            if (isAlone) return@forEach

            var directionIndex = 0

            while (directionIndex < 4) {
                val direction = (round + directionIndex) % 4

                val proposal = when {
                    direction == 0 && (-1..1).none { Vec2(elf.x + it, elf.y - 1) in elves } -> {
                        Vec2(elf.x, elf.y - 1)
                    }

                    direction == 1 && (-1..1).none { Vec2(elf.x + it, elf.y + 1) in elves } -> {
                        Vec2(elf.x, elf.y + 1)
                    }

                    direction == 2 && (-1..1).none { Vec2(elf.x - 1, elf.y + it) in elves } -> {
                        Vec2(elf.x - 1, elf.y)
                    }

                    direction == 3 && (-1..1).none { Vec2(elf.x + 1, elf.y + it) in elves } -> {
                        Vec2(elf.x + 1, elf.y)
                    }

                    else -> null
                }

                directionIndex++

                if (proposal != null) {
                    proposals[proposal] = if (proposal in proposals) {
                        Vec2(Int.MAX_VALUE, Int.MAX_VALUE)
                    } else {
                        elf
                    }
                    break
                }
            }
        }

        var hasMoved = false

        proposals.forEach { (destination, elf) ->
            if (elf.x == Int.MAX_VALUE) return@forEach
            elves -= elf
            elves += destination
            hasMoved = true
        }

        return hasMoved
    }
}