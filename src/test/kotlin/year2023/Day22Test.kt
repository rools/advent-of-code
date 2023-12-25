package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day22.solve1
import year2023.Day22.solve2
import kotlin.test.assertEquals

class Day22Test : DayTest(year = 2023, day = 22) {
    @Test
    fun part1Sample() {
        assertEquals(5, solve1(input("sample")))
    }

    @Test
    fun part1Input() {
        assertEquals(437, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(7, solve2(input("sample")))
    }

    @Test
    fun part2Input() {
        assertEquals(42561, solve2(input("input")))
    }
}