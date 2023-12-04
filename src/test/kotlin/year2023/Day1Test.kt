package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day1.solve1
import year2023.Day1.solve2
import kotlin.test.assertEquals

class Day1Test : DayTest(year = 2023, day = 1) {
    @Test
    fun part1Sample() {
        assertEquals(142, solve1(input("sample1")))
    }

    @Test
    fun part1Input() {
        assertEquals(55816, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(281, solve2(input("sample2")))
    }

    @Test
    fun part2Input() {
        assertEquals(54980, solve2(input("input")))
    }
}