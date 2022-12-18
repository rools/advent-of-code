package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day10.solve1
import year2022.Day10.solve2
import kotlin.test.assertEquals

class Day10Test : DayTest(year = 2022, day = 10) {
    @Test
    fun part1Sample() = assertEquals(
        expected = 13140,
        actual = solve1(input("sample"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = 14340,
        actual = solve1(input("input"))
    )

    @Test
    fun part2Sample() = assertEquals(
        expected = """
            ##..##..##..##..##..##..##..##..##..##..
            ###...###...###...###...###...###...###.
            ####....####....####....####....####....
            #####.....#####.....#####.....#####.....
            ######......######......######......####
            #######.......#######.......#######.....
        """.trimIndent(),
        actual = solve2(input("sample"))
    )

    @Test
    fun part2Input() = assertEquals(
        expected = """
            ###...##..###....##..##..###..#..#.###..
            #..#.#..#.#..#....#.#..#.#..#.#..#.#..#.
            #..#.#..#.#..#....#.#....###..####.#..#.
            ###..####.###.....#.#....#..#.#..#.###..
            #....#..#.#....#..#.#..#.#..#.#..#.#....
            #....#..#.#.....##...##..###..#..#.#....
        """.trimIndent(),
        actual = solve2(input("input"))
    )
}