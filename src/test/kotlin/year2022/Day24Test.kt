package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day24.solve1
import year2022.Day24.solve2
import kotlin.test.assertEquals

class Day24Test : DayTest(year = 2022, day = 24) {
    @Test
    fun part1Sample() = assertEquals(
        expected = 18,
        actual = solve1(input("sample"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = 334,
        actual = solve1(input("input"))
    )

    @Test
    fun part2Sample() = assertEquals(
        expected = 54,
        actual = solve2(input("sample"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = 934,
        actual = solve2(input("input"))
    )
}