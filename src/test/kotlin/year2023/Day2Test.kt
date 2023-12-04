package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day2.solve1
import year2023.Day2.solve2
import kotlin.test.assertEquals

class Day2Test : DayTest(year = 2023, day = 2) {
    @Test
    fun part1Sample() {
        assertEquals(8, solve1(input("sample")))
    }

    @Test
    fun part1Input() {
        assertEquals(2204, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(2286, solve2(input("sample")))
    }

    @Test
    fun part2Input() {
        assertEquals(71036, solve2(input("input")))
    }
}