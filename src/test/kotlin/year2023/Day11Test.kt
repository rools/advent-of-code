package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day11.solve1
import year2023.Day11.solve2
import kotlin.test.assertEquals

class Day11Test : DayTest(year = 2023, day = 11) {
    @Test
    fun part1Sample() {
        assertEquals(374, solve1(input("sample")))
    }

    @Test
    fun part1Input() {
        assertEquals(10276166, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(8410, solve2(input("sample"), expansion = 100))
    }

    @Test
    fun part2Input() {
        assertEquals(598693078798, solve2(input("input"), expansion = 1_000_000))
    }
}