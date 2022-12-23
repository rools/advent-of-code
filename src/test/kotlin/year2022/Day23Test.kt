package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day23.solve1
import year2022.Day23.solve2
import kotlin.test.assertEquals

class Day23Test : DayTest(year = 2022, day = 23) {
    @Test
    fun part1Sample() = assertEquals(
        expected = 110,
        actual = solve1(input("sample"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = 3990,
        actual = solve1(input("input"))
    )

    @Test
    fun part2Sample() = assertEquals(
        expected = 20,
        actual = solve2(input("sample"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = 1057,
        actual = solve2(input("input"))
    )
}