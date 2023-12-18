package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day18.solve1
import year2023.Day18.solve2
import kotlin.test.assertEquals

class Day18Test : DayTest(year = 2023, day = 18) {
    @Test
    fun part1Sample() {
        assertEquals(62, solve1(input("sample")))
    }

    @Test
    fun part1Input() {
        assertEquals(70253, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(952408144115, solve2(input("sample")))
    }

    @Test
    fun part2Input() {
        assertEquals(131265059885080, solve2(input("input")))
    }
}