package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day15.solve1
import year2022.Day15.solve2
import kotlin.test.assertEquals

class Day15Test : DayTest(year = 2022, day = 15) {
    @Test
    fun part1Sample() = assertEquals(
        expected = 26,
        actual = solve1(input("sample"), targetY = 10)
    )

    @Test
    fun part1Input() = assertEquals(
        expected = 5108096,
        actual = solve1(input("input"), targetY = 2_000_000)
    )

    @Test
    fun part2Sample() = assertEquals(
        expected = 56000011L,
        actual = solve2(input("sample"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = 10553942650264L,
        actual = solve2(input("input"))
    )
}