package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day22.solve1
import year2022.Day22.solve2
import kotlin.test.assertEquals

class Day22Test : DayTest(year = 2022, day = 22) {
    @Test
    fun part1Sample() = assertEquals(
        expected = 6032,
        actual = solve1(input("sample"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = 136054,
        actual = solve1(input("input"))
    )

    @Test
    fun part2Sample() = assertEquals(
        expected = 5031,
        actual = solve2(input("sample"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = 122153,
        actual = solve2(input("input"))
    )
}