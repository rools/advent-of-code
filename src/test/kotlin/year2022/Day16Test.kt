package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day16.solve1
import year2022.Day16.solve2
import kotlin.test.assertEquals

class Day16Test : DayTest(year = 2022, day = 16) {
    @Test
    fun part1Sample() = assertEquals(
        expected = 1651,
        actual = solve1(input("sample"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = 1862,
        actual = solve1(input("input"))
    )

    @Test
    fun part2Sample() = assertEquals(
        expected = 1707,
        actual = solve2(input("sample"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = 2422,
        actual = solve2(input("input"))
    )
}