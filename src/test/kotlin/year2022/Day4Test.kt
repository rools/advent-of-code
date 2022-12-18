package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day4.solve1
import year2022.Day4.solve2
import kotlin.test.assertEquals

class Day4Test : DayTest(year = 2022, day = 4) {
    @Test
    fun part1Sample() = assertEquals(
        expected = 2,
        actual = solve1(input("sample"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = 466,
        actual = solve1(input("input"))
    )

    @Test
    fun part2Sample() = assertEquals(
        expected = 4,
        actual = solve2(input("sample"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = 865,
        actual = solve2(input("input"))
    )
}