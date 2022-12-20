package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day19.solve1
import year2022.Day19.solve2
import kotlin.test.assertEquals

class Day19Test : DayTest(year = 2022, day = 19) {
    @Test
    fun part1Sample() = assertEquals(
        expected = 33,
        actual = solve1(input("sample"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = 1294,
        actual = solve1(input("input"))
    )

    @Test
    fun part2Sample() = assertEquals(
        expected = 3472,
        actual = solve2(input("sample"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = 13640,
        actual = solve2(input("input"))
    )
}