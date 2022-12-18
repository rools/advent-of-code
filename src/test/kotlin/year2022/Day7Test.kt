package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day7.solve1
import year2022.Day7.solve2
import kotlin.test.assertEquals

class Day7Test : DayTest(year = 2022, day = 7) {
    @Test
    fun part1Sample() = assertEquals(
        expected = 95437,
        actual = solve1(input("sample"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = 1182909,
        actual = solve1(input("input"))
    )

    @Test
    fun part2Sample() = assertEquals(
        expected = 24933642,
        actual = solve2(input("sample"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = 2832508,
        actual = solve2(input("input"))
    )
}