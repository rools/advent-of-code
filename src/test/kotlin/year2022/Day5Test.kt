package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day5.solve1
import year2022.Day5.solve2
import kotlin.test.assertEquals

class Day5Test : DayTest(year = 2022, day = 5) {
    @Test
    fun part1Sample() = assertEquals(
        expected = "CMZ",
        actual = solve1(input("sample"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = "FWSHSPJWM",
        actual = solve1(input("input"))
    )

    @Test
    fun part2Sample() = assertEquals(
        expected = "MCD",
        actual = solve2(input("sample"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = "PWPWHGFZS",
        actual = solve2(input("input"))
    )
}