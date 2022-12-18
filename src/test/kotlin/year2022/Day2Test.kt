package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day2.solve1
import year2022.Day2.solve2
import kotlin.test.assertEquals

class Day2Test : DayTest(year = 2022, day = 2) {
    @Test
    fun part1Sample() {
        assertEquals(15, solve1(input("sample")))
    }

    @Test
    fun part1Input() {
        assertEquals(10595, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(12, solve2(input("sample")))
    }

    @Test
    fun part2Input() {
        assertEquals(9541, solve2(input("input")))
    }
}