package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day4.solve1
import year2023.Day4.solve2
import kotlin.test.assertEquals

class Day4Test : DayTest(year = 2023, day = 4) {
    @Test
    fun part1Sample() {
        assertEquals(13, solve1(input("sample")))
    }

    @Test
    fun part1Input() {
        assertEquals(15205, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(30, solve2(input("sample")))
    }

    @Test
    fun part2Input() {
        assertEquals(6189740, solve2(input("input")))
    }
}