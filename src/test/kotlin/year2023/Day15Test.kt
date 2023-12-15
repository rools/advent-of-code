package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day15.solve1
import year2023.Day15.solve2
import kotlin.test.assertEquals

class Day15Test : DayTest(year = 2023, day = 15) {
    @Test
    fun part1Sample() {
        assertEquals(1320, solve1(input("sample")))
    }

    @Test
    fun part1Input() {
        assertEquals(510273, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(145, solve2(input("sample")))
    }

    @Test
    fun part2Input() {
        assertEquals(212449, solve2(input("input")))
    }
}