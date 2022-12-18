package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day1.solve1
import year2022.Day1.solve2
import kotlin.test.assertEquals

class Day1Test : DayTest(year = 2022, day = 1) {
    @Test
    fun part1Sample() {
        assertEquals(24000, solve1(input("sample")))
    }

    @Test
    fun part1Input() {
        assertEquals(66616, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(45000, solve2(input("sample")))
    }

    @Test
    fun part2Input() {
        assertEquals(199172, solve2(input("input")))
    }
}