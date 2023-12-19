package year2023

import DayTest
import org.junit.jupiter.api.Test
import year2023.Day19.solve1
import year2023.Day19.solve2
import kotlin.test.assertEquals

class Day19Test : DayTest(year = 2023, day = 19) {
    @Test
    fun part1Sample() {
        assertEquals(19114, solve1(input("sample")))
    }

    @Test
    fun part1Input() {
        assertEquals(420739, solve1(input("input")))
    }

    @Test
    fun part2Sample() {
        assertEquals(167409079868000, solve2(input("sample")))
    }

    @Test
    fun part2Input() {
        assertEquals(130251901420382, solve2(input("input")))
    }
}