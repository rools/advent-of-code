package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day7.solve1
import year2023.Day7.solve2
import kotlin.test.assertEquals

class Day7Test : DayTest(year = 2023, day = 7) {
    @Test
    fun part1Sample() {
        assertEquals(6440L, solve1(input("sample")))
    }

    @Test
    fun part1Input() {
        assertEquals(252295678L, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(5905L, solve2(input("sample")))
    }

    @Test
    fun part2Input() {
        assertEquals(250577259L, solve2(input("input")))
    }
}