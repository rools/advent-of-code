package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day6.solve1
import year2023.Day6.solve2
import kotlin.test.assertEquals

class Day6Test : DayTest(year = 2023, day = 6) {
    @Test
    fun part1Sample() {
        assertEquals(288, solve1(input("sample")))
    }

    @Test
    fun part1Input() {
        assertEquals(275724, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(71503, solve2(input("sample")))
    }

    @Test
    fun part2Input() {
        assertEquals(37286485, solve2(input("input")))
    }
}