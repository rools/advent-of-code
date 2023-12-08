package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day8.solve1
import year2023.Day8.solve2
import kotlin.test.assertEquals

class Day8Test : DayTest(year = 2023, day = 8) {
    @Test
    fun part1Sample1() {
        assertEquals(2, solve1(input("sample1")))
    }

    @Test
    fun part1Sample2() {
        assertEquals(6, solve1(input("sample2")))
    }

    @Test
    fun part1Input() {
        assertEquals(13301, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(6, solve2(input("sample3")))
    }

    @Test
    fun part2Input() {
        assertEquals(7309459565207L, solve2(input("input")))
    }
}