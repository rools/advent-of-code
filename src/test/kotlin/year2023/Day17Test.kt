package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day17.solve1
import year2023.Day17.solve2
import kotlin.test.assertEquals

class Day17Test : DayTest(year = 2023, day = 17) {
    @Test
    fun part1Sample() {
        assertEquals(102, solve1(input("sample")))
    }

    @Test
    fun part1Input() {
        assertEquals(967, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(94, solve2(input("sample")))
    }

    @Test
    fun part2Input() {
        assertEquals(1101, solve2(input("input")))
    }
}