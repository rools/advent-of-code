package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day3.solve1
import year2022.Day3.solve2
import kotlin.test.assertEquals

class Day3Test : DayTest(year = 2022, day = 3) {
    @Test
    fun part1Sample() {
        assertEquals(157, solve1(input("sample")))
    }

    @Test
    fun part1Input() {
        assertEquals(8515, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(70, solve2(input("sample")))
    }

    @Test
    fun part2Input() {
        assertEquals(2434, solve2(input("input")))
    }
}