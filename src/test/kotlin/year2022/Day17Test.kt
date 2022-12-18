package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day17.solve1
import year2022.Day17.solve2
import kotlin.test.assertEquals

class Day17Test : DayTest(year = 2022, day = 17) {
    @Test
    fun part1Sample() = assertEquals(
        expected = 3068,
        actual = solve1(input("sample"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = 3147,
        actual = solve1(input("input"))
    )

    @Test
    fun part2Sample() = assertEquals(
        expected = 1514285714288L,
        actual = solve2(input("sample"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = 1532163742758L,
        actual = solve2(input("input"))
    )
}