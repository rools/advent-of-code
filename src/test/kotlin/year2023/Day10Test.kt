package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day10.solve1
import year2023.Day10.solve2
import kotlin.test.assertEquals

class Day10Test : DayTest(year = 2023, day = 10) {
    @Test
    fun part1Sample() {
        assertEquals(4, solve1(input("sample1")))
    }

    @Test
    fun part1Sample2() {
        assertEquals(8, solve1(input("sample2")))
    }

    @Test
    fun part1Input() {
        assertEquals(6738, solve1(input("input")))
    }

    @Test
    fun part2Sample1() {
        assertEquals(4, solve2(input("sample3")))
    }

    @Test
    fun part2Sample2() {
        assertEquals(8, solve2(input("sample4")))
    }

    @Test
    fun part2Input() {
        assertEquals(579, solve2(input("input")))
    }
}