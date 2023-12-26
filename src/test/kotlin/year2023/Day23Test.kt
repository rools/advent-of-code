package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day23.solve1
import year2023.Day23.solve2
import kotlin.test.assertEquals

class Day23Test : DayTest(year = 2023, day = 23) {
    @Test
    fun part1Sample() {
        assertEquals(94, solve1(input("sample")))
    }

    @Test
    fun part1Input() {
        assertEquals(2318, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(154, solve2(input("sample")))
    }

    @Test
    fun part2Input() {
        assertEquals(6426, solve2(input("input")))
    }
}