package year2022

import kotlin.math.absoluteValue

object Day10 {
    fun solve1(input: List<String>): Int {
        var x = 1
        var cycles = 1
        var nextCycleCount = 20
        var sum = 0

        input.forEach { line ->
            val parts = line.split(' ')
            val c = if (parts[0] == "addx") 1 else 0
            if (cycles + c >= nextCycleCount) {
                sum += nextCycleCount * x
                nextCycleCount += 40
            }

            if (parts[0] == "addx") {
                cycles += 2
                x += parts[1].toInt()
            } else {
                cycles++
            }
        }

        return sum
    }

    fun solve2(input: List<String>): String {
        var x = 1
        var cycles = 0
        val crt = Array(6) {
            CharArray(40) { '.' }
        }

        input.forEach { line ->
            val parts = line.split(' ')
            val c = if (parts[0] == "addx") 2 else 1

            repeat(c) { c2 ->
                val crtX = (cycles + c2) % 40
                if ((crtX - x).absoluteValue <= 1) {
                    val crtY = (cycles + c2) / 40
                    crt[crtY][crtX] = '#'
                }
            }

            if (parts[0] == "addx") {
                cycles += 2
                x += parts[1].toInt()
            } else {
                cycles++
            }
        }

        return crt.joinToString(separator = "\n") { String(it) }
    }
}