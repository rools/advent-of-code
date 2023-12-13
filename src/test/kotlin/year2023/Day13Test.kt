package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day13.solve1
import year2023.Day13.solve2
import kotlin.test.assertEquals

class Day13Test : DayTest(year = 2023, day = 13) {
    @Test
    fun part1Sample() {
        assertEquals(405, solve1(input("sample")))
    }

    @Test
    fun part1Input() {
        assertEquals(36448, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(400, solve2(input("sample")))
    }

    @Test
    fun part2Input() {
        assertEquals(35799, solve2(input("input")))
    }
}