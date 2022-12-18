package year2022

import lib.algos.split

object Day11 {
    fun solve1(input: List<String>): Long {
        return simulate(input, rounds = 20, divideWorryBy = 3)
    }

    fun solve2(input: List<String>): Long {
        return simulate(input, rounds = 10_000, divideWorryBy = 1)
    }

    private fun parseMonkeys(input: List<String>): List<Monkey> {
        return input.split("")
            .map { parseMonkey(it) }
    }

    private fun parseMonkey(input: List<String>): Monkey {
        val operation = input[2].substringAfter("Operation: new = ")
            .split(" ")

        return Monkey(
            items = input[1]
                .substringAfter("Starting items: ")
                .split(", ")
                .map { it.toLong() }
                .toMutableList(),
            operator = operation[1],
            operand1 = operation[0],
            operand2 = operation[2],
            divideBy = input[3]
                .substringAfter("Test: divisible by ")
                .toInt(),
            trueMonkey = input[4]
                .substringAfter("If true: throw to monkey ")
                .toInt(),
            falseMonkey = input[5]
                .substringAfter("If false: throw to monkey ")
                .toInt()
        )
    }

    private fun Monkey.performOperation(item: Long): Long {
        val value1 = if (operand1 == "old") item else operand1.toLong()
        val value2 = if (operand2 == "old") item else operand2.toLong()
        return when (operator) {
            "*" -> value1 * value2
            else -> value1 + value2
        }
    }

    private fun Monkey.getNextMonkey(item: Long): Int {
        return when (item % divideBy) {
            0L -> trueMonkey
            else -> falseMonkey
        }
    }

    private fun simulate(input: List<String>, rounds: Int, divideWorryBy: Int): Long {
        val monkeys = parseMonkeys(input)
        val modulo = monkeys.fold(1) { acc, monkey -> acc * monkey.divideBy }

        repeat(rounds) {
            monkeys.forEach { monkey ->
                monkey.items.forEach { item ->
                    val newItem = monkey.performOperation(item) / divideWorryBy % modulo
                    val nextMonkey = monkey.getNextMonkey(newItem)
                    monkeys[nextMonkey].items += newItem
                }
                monkey.inspectCount += monkey.items.size
                monkey.items.clear()
            }
        }

        val inspectCounts = monkeys.map { it.inspectCount }
            .sortedDescending()

        return inspectCounts[0] * inspectCounts[1]
    }

    private data class Monkey(
        val items: MutableList<Long>,
        val operator: String,
        val operand1: String,
        val operand2: String,
        val divideBy: Int,
        val trueMonkey: Int,
        val falseMonkey: Int,
        var inspectCount: Long = 0
    )
}