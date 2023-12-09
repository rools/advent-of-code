package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day9.solve1
import year2023.Day9.solve2
import kotlin.test.assertEquals

class Day9Test : DayTest(year = 2023, day = 9) {
    @Test
    fun part1Sample() {
        assertEquals(114, solve1(input("sample")))
    }

    @Test
    fun part1Input() {
        assertEquals(1934898178, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(2, solve2(input("sample")))
    }

    @Test
    fun part2Input() {
        assertEquals(1129, solve2(input("input")))
    }
}