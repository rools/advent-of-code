package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day12.solve1
import year2023.Day12.solve2
import kotlin.test.assertEquals

class Day12Test : DayTest(year = 2023, day = 12) {
    @Test
    fun part1Sample() {
        assertEquals(21, solve1(input("sample")))
    }

    @Test
    fun part1Input() {
        assertEquals(7204, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(525152, solve2(input("sample")))
    }

    @Test
    fun part2Input() {
        assertEquals(1672318386674, solve2(input("input")))
    }
}