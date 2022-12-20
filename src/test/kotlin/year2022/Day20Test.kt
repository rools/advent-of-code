package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day20.solve1
import year2022.Day20.solve2
import kotlin.test.assertEquals

class Day20Test : DayTest(year = 2022, day = 20) {
    @Test
    fun part1Sample() = assertEquals(
        expected = 3L,
        actual = solve1(input("sample"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = 4578L,
        actual = solve1(input("input"))
    )

    @Test
    fun part2Sample() = assertEquals(
        expected = 1623178306L,
        actual = solve2(input("sample"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = 2159638736133L,
        actual = solve2(input("input"))
    )
}