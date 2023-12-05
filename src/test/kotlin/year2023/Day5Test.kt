package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day5.solve1
import year2023.Day5.solve2
import kotlin.test.assertEquals

class Day5Test : DayTest(year = 2023, day = 5) {
    @Test
    fun part1Sample() {
        assertEquals(35L, solve1(input("sample")))
    }

    @Test
    fun part1Input() {
        assertEquals(165788812L, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(46L, solve2(input("sample")))
    }

    @Test
    fun part2Input() {
        assertEquals(1928058L, solve2(input("input")))
    }
}