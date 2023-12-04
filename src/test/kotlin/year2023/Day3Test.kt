package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day3.solve1
import year2023.Day3.solve2
import kotlin.test.assertEquals

class Day3Test : DayTest(year = 2023, day = 3) {
    @Test
    fun part1Sample() {
        assertEquals(4361, solve1(input("sample")))
    }

    @Test
    fun part1Input() {
        assertEquals(530849, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(467835, solve2(input("sample")))
    }

    @Test
    fun part2Input() {
        assertEquals(84900879, solve2(input("input")))
    }
}