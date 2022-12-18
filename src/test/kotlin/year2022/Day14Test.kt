package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day14.solve1
import year2022.Day14.solve2
import kotlin.test.assertEquals

class Day14Test : DayTest(year = 2022, day = 14) {
    @Test
    fun part1Sample() = assertEquals(
        expected = 24,
        actual = solve1(input("sample"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = 873,
        actual = solve1(input("input"))
    )

    @Test
    fun part2Sample() = assertEquals(
        expected = 93,
        actual = solve2(input("sample"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = 24813,
        actual = solve2(input("input"))
    )
}