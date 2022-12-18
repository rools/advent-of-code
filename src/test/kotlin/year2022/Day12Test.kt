package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day12.solve1
import year2022.Day12.solve2
import kotlin.test.assertEquals

class Day12Test : DayTest(year = 2022, day = 12) {
    @Test
    fun part1Sample() = assertEquals(
        expected = 31,
        actual = solve1(input("sample"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = 520,
        actual = solve1(input("input"))
    )

    @Test
    fun part2Sample() = assertEquals(
        expected = 29,
        actual = solve2(input("sample"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = 508,
        actual = solve2(input("input"))
    )
}