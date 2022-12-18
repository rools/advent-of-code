package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day8.solve1
import year2022.Day8.solve2
import kotlin.test.assertEquals

class Day8Test : DayTest(year = 2022, day = 8) {
    @Test
    fun part1Sample() = assertEquals(
        expected = 21,
        actual = solve1(input("sample"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = 1840,
        actual = solve1(input("input"))
    )

    @Test
    fun part2Sample() = assertEquals(
        expected = 8,
        actual = solve2(input("sample"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = 405769,
        actual = solve2(input("input"))
    )
}