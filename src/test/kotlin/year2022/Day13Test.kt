package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day13.solve1
import year2022.Day13.solve2
import kotlin.test.assertEquals

class Day13Test : DayTest(year = 2022, day = 13) {
    @Test
    fun part1Sample() = assertEquals(
        expected = 13,
        actual = solve1(input("sample"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = 6478,
        actual = solve1(input("input"))
    )

    @Test
    fun part2Sample() = assertEquals(
        expected = 140,
        actual = solve2(input("sample"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = 21922,
        actual = solve2(input("input"))
    )
}