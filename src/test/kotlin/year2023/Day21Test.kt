package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day21.solve1
import year2023.Day21.solve2
import kotlin.test.assertEquals

class Day21Test : DayTest(year = 2023, day = 21) {
    @Test
    fun part1Sample() {
        assertEquals(16, solve1(input("sample"), maxSteps = 6))
    }

    @Test
    fun part1Input() {
        assertEquals(3632, solve1(input("input"), maxSteps = 64))
    }

    @Test
    fun part2Sample() {
        assertEquals(16733044, solve2(input("sample"), maxSteps = 5000))
    }

    @Test
    fun part2Input() {
        assertEquals(600336060511101, solve2(input("input"), maxSteps = 26501365))
    }
}