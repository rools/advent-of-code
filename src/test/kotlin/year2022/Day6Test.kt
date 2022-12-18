package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day6.solve1
import year2022.Day6.solve2
import kotlin.test.assertEquals

class Day6Test : DayTest(year = 2022, day = 6) {
    @Test
    fun part1Sample1() = assertEquals(
        expected = 7,
        actual = solve1(input("sample1"))
    )

    @Test
    fun part1Sample2() = assertEquals(
        expected = 5,
        actual = solve1(input("sample2"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = 1848,
        actual = solve1(input("input"))
    )

    @Test
    fun part2Sample1() = assertEquals(
        expected = 19,
        actual = solve2(input("sample1"))
    )

    @Test
    fun part2Sample2() = assertEquals(
        expected = 23,
        actual = solve2(input("sample2"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = 2308,
        actual = solve2(input("input"))
    )
}