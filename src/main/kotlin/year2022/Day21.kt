package year2022

import year2022.Day21.Expression.*
import year2022.Day21.Operator.*

object Day21 {
    fun solve1(input: List<String>): Long {
        return getExpression(input, includeUnknown = false).evaluate()
    }

    fun solve2(input: List<String>): Long {
        return solve(getExpression(input, includeUnknown = true))
    }

    private fun getExpression(input: List<String>, includeUnknown: Boolean): Expression {
        val monkeys = input.associate { line ->
            val parts = line.split(": ")
            parts[0] to parts[1]
        }

        fun getExpression(monkey: String, monkeys: Map<String, String>): Expression {
            val parts = monkeys.getValue(monkey).split(" ")

            return if (includeUnknown && monkey == "humn") {
                return Unknown
            } else if (parts.size == 1) {
                Literal(parts[0].toLong())
            } else {
                Operation(
                    left = getExpression(parts[0], monkeys),
                    right = getExpression(parts[2], monkeys),
                    operator = when (parts[1]) {
                        "+" -> ADD
                        "-" -> SUB
                        "*" -> MUL
                        else -> DIV
                    }
                )
            }
        }

        return getExpression("root", monkeys)
    }

    private fun solve(expression: Expression): Long {
        if (expression !is Operation) return 0

        var (unknown, known) = when (expression.left.hasUnknown()) {
            true -> expression.left to expression.right
            false -> expression.right to expression.left
        }

        while (true) {
            when (val currentUnknown = unknown) {
                is Operation -> {
                    val left = currentUnknown.left
                    val right = currentUnknown.right

                    if (left.hasUnknown()) {
                        unknown = left
                        known = when (currentUnknown.operator) {
                            ADD -> Operation(known, right, SUB)
                            SUB -> Operation(known, right, ADD)
                            MUL -> Operation(known, right, DIV)
                            DIV -> Operation(known, right, MUL)
                        }
                    } else {
                        unknown = right
                        known = when (currentUnknown.operator) {
                            ADD -> Operation(known, left, SUB)
                            SUB -> Operation(Operation(known, left, SUB), Literal(-1), MUL)
                            MUL -> Operation(known, left, DIV)
                            DIV -> Operation(known, left, DIV)
                        }
                    }
                }

                is Unknown -> {
                    return known.evaluate()
                }

                is Literal -> error("No unknown found")
            }
        }
    }

    private fun Expression.hasUnknown(): Boolean = when (this) {
        is Operation -> left.hasUnknown() || right.hasUnknown()
        is Literal -> false
        is Unknown -> true
    }

    private fun Expression.evaluate(): Long = when (this) {
        is Operation -> {
            val left = left.evaluate()
            val right = right.evaluate()
            when (operator) {
                ADD -> left + right
                SUB -> left - right
                MUL -> left * right
                DIV -> left / right
            }
        }

        is Literal -> value
        is Unknown -> error("Can't evaluate unknown")
    }

    enum class Operator { ADD, SUB, MUL, DIV }

    sealed interface Expression {
        data class Operation(
            val left: Expression,
            val right: Expression,
            val operator: Operator
        ) : Expression

        data class Literal(
            val value: Long
        ) : Expression

        object Unknown : Expression
    }
}