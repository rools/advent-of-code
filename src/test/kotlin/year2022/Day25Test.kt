package year2022

import DayTest
import org.junit.jupiter.api.Test
import year2022.Day25.solve1
import kotlin.test.assertEquals

class Day25Test : DayTest(year = 2022, day = 25) {
    @Test
    fun part1Sample() = assertEquals(
        expected = "2=-1=0",
        actual = solve1(input("sample"))
    )

    @Test
    fun part1Input() = assertEquals(
        expected = "2-==10--=-0101==1201",
        actual = solve1(input("input"))
    )
}