package year2022

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
                Number(parts[0].toLong())
            } else {
                Operation(
                    left = getExpression(parts[0], monkeys),
                    right = getExpression(parts[2], monkeys),
                    operation = parts[1]
                )
            }
        }

        return getExpression("root", monkeys)
    }

    private fun solve(expression: Expression): Long {
        if (expression !is Operation) return 0

        var left = expression.left
        var right = expression.right

        if (right.hasUnknown()) {
            left = expression.right
            right = expression.left
        }

        while (true) {
            when (val currentLeft = left) {
                is Operation -> {
                    val l = currentLeft.left
                    val r = currentLeft.right
                    val leftUnknown = l.hasUnknown()

                    when (currentLeft.operation) {
                        "+" -> {
                            if (leftUnknown) {
                                left = l
                                right = Operation(right, r, "-")
                            } else {
                                left = r
                                right = Operation(right, l, "-")
                            }
                        }

                        "-" -> {
                            if (leftUnknown) {
                                left = l
                                right = Operation(right, r, "+")
                            } else {
                                left = r
                                right = Operation(Operation(right, l, "-"), Number(-1), "*")
                            }
                        }

                        "*" -> {
                            if (leftUnknown) {
                                left = l
                                right = Operation(right, r, "/")
                            } else {
                                left = r
                                right = Operation(right, l, "/")
                            }
                        }

                        "/" -> {
                            if (leftUnknown) {
                                left = l
                                right = Operation(right, r, "*")
                            } else {
                                left = r
                                right = Operation(l, right, "/")
                            }
                        }
                    }
                }

                is Unknown -> {
                    return right.evaluate()
                }

                is Number -> error("No unknown found")
            }
        }
    }

    private fun Expression.hasUnknown(): Boolean {
        return when (this) {
            is Operation -> left.hasUnknown() || right.hasUnknown()
            is Number -> false
            is Unknown -> true
        }
    }

    private fun Expression.evaluate(): Long {
        return when (this) {
            is Operation -> {
                val left = left.evaluate()
                val right = right.evaluate()
                when (operation) {
                    "+" -> left + right
                    "-" -> left - right
                    "*" -> left * right
                    else -> left / right
                }
            }

            is Number -> value
            is Unknown -> error("Can't evaluate unknown")
        }
    }

    sealed interface Expression

    data class Operation(
        val left: Expression,
        val right: Expression,
        val operation: String
    ) : Expression

    data class Number(
        val value: Long
    ) : Expression

    object Unknown : Expression
}