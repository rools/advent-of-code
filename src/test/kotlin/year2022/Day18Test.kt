package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day18.solve1
import year2022.Day18.solve2
import kotlin.test.assertEquals

class Day18Test : DayTest(year = 2022, day = 18) {
    @Test
    fun part1Sample() = assertEquals(
        expected = 64,
        actual = solve1(input("sample"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = 4314,
        actual = solve1(input("input"))
    )

    @Test
    fun part2Sample() = assertEquals(
        expected = 58,
        actual = solve2(input("sample"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = 2444,
        actual = solve2(input("input"))
    )
}