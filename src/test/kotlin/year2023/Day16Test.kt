package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day16.solve1
import year2023.Day16.solve2
import kotlin.test.assertEquals

class Day16Test : DayTest(year = 2023, day = 16) {
    @Test
    fun part1Sample() {
        assertEquals(46, solve1(input("sample")))
    }

    @Test
    fun part1Input() {
        assertEquals(8901, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(51, solve2(input("sample")))
    }

    @Test
    fun part2Input() {
        assertEquals(9064, solve2(input("input")))
    }
}