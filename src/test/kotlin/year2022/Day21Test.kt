package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day21.solve1
import year2022.Day21.solve2
import kotlin.test.assertEquals

class Day21Test : DayTest(year = 2022, day = 21) {
    @Test
    fun part1Sample() = assertEquals(
        expected = 152,
        actual = solve1(input("sample"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = 276156919469632,
        actual = solve1(input("input"))
    )

    @Test
    fun part2Sample() = assertEquals(
        expected = 301,
        actual = solve2(input("sample"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = 3441198826073,
        actual = solve2(input("input"))
    )
}