package year2023

import java.util.*

object Day7 {
    fun solve1(input: List<String>): Long {
        return calculateWinnings(
            input,
            strengths = "AKQJT98765432",
            handleJokers = false
        )
    }

    fun solve2(input: List<String>): Long {
        return calculateWinnings(
            input,
            strengths = "AKQT98765432J",
            handleJokers = true
        )
    }

    private fun calculateWinnings(
        input: List<String>,
        strengths: String,
        handleJokers: Boolean
    ): Long {
        val hands = input.map { it.split(' ') }
            .map { it[0] to it[1].toLong() }

        val sorted = hands.sortedWith { hand1, hand2 ->
            val type1 = getType(hand1.first, handleJokers)
            val type2 = getType(hand2.first, handleJokers)
            when {
                type1 > type2 -> -1
                type1 < type2 -> 1
                else -> Arrays.compare(
                    hand2.first.map { strengths.indexOf(it) }.toTypedArray(),
                    hand1.first.map { strengths.indexOf(it) }.toTypedArray()
                )
            }
        }

        return sorted.withIndex().sumOf { (index, hand) ->
            (index + 1) * hand.second
        }
    }

    private fun getType(hand: String, handleJokers: Boolean): Int {
        val counts = hand.groupBy { it }
            .filter { !handleJokers || it.key != 'J' }
            .map { it.value.size }
            .sortedDescending() + listOf(0, 0)
        val jokers = hand.count { handleJokers && it == 'J' }
        return when {
            (counts[0] + jokers) == 5 -> 1
            (counts[0] + jokers) == 4 -> 2
            (counts[0] + jokers) == 3 && counts[1] == 2 -> 3
            (counts[0] + jokers) == 3 -> 4
            (counts[0] + jokers) == 2 && counts[1] == 2 -> 5
            (counts[0] + jokers) == 2 -> 6
            else -> 7
        }
    }
}