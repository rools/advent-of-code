package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day11.solve1
import year2022.Day11.solve2
import kotlin.test.assertEquals

class Day11Test : DayTest(year = 2022, day = 11) {
    @Test
    fun part1Sample() = assertEquals(
        expected = 10605L,
        actual = solve1(input("sample"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = 110220L,
        actual = solve1(input("input"))
    )

    @Test
    fun part2Sample() = assertEquals(
        expected = 2713310158L,
        actual = solve2(input("sample"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = 19457438264L,
        actual = solve2(input("input"))
    )
}