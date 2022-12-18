package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day9.solve1
import year2022.Day9.solve2
import kotlin.test.assertEquals

class Day9Test : DayTest(year = 2022, day = 9) {
    @Test
    fun part1Sample1() = assertEquals(
        expected = 13,
        actual = solve1(input("sample1"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = 6269,
        actual = solve1(input("input"))
    )

    @Test
    fun part2Sample1() = assertEquals(
        expected = 1,
        actual = solve2(input("sample1"))
    )

    @Test
    fun part2Sample2() = assertEquals(
        expected = 36,
        actual = solve2(input("sample2"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = 2557,
        actual = solve2(input("input"))
    )
}