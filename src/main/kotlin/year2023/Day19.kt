package year2023

import lib.algos.split

object Day19 {

    fun solve1(input: List<String>): Long {
        val (workflowsInput, partsInput) = input.split("")
        val workflows = parseWorkflows(workflowsInput)
        val parts = parseParts(partsInput)

        return parts.filter { isAccepted(it, workflows) }
            .sumOf { it.values.sum() }
    }

    fun solve2(input: List<String>): Long {
        val (workflowsInput, _) = input.split("")
        val workflows = parseWorkflows(workflowsInput)

        return calculateAcceptedCount(
            workflowName = "in",
            ratingRanges = listOf("x", "m", "a", "s").associateWith { 1L..4000L },
            workflows = workflows
        )
    }

    private fun calculateAcceptedCount(
        workflowName: String,
        workflows: Map<String, List<Rule>>,
        ratingRanges: Map<String, LongRange>
    ): Long {
        when (workflowName) {
            "A" -> return ratingRanges.values.fold(1L) { product, range -> product * (range.last - range.first + 1) }
            "R" -> return 0
        }

        val workFlow = workflows.getValue(workflowName)

        val newRanges = ratingRanges.toMutableMap()

        return workFlow.sumOf { rule ->
            val range = newRanges[rule.category] ?: 0L..0L

            when (rule.operator) {
                '<' -> newRanges[rule.category] = range.first..(rule.value - 1)
                '>' -> newRanges[rule.category] = (rule.value + 1)..range.last
            }

            val acceptedCount = calculateAcceptedCount(rule.goto, workflows, newRanges)

            when (rule.operator) {
                '<' -> newRanges[rule.category] = rule.value..range.last
                '>' -> newRanges[rule.category] = range.first..rule.value
            }

            acceptedCount
        }
    }

    private fun parseWorkflows(input: List<String>): Map<String, List<Rule>> {
        return input.associate { line ->
            val name = line.substringBefore('{')
            name to line.substringAfter("{")
                .substringBefore("}")
                .split(',')
                .map { parseRule(it) }
        }
    }

    private fun parseRule(input: String): Rule {
        if (':' !in input) {
            return Rule(
                category = "",
                operator = null,
                value = 0,
                goto = input
            )
        }
        return Rule(
            category = input[0].toString(),
            operator = input[1],
            value = input.substring(2).substringBefore(':').toLong(),
            goto = input.substringAfter(':')
        )
    }

    private fun parseParts(input: List<String>): List<Map<String, Long>> {
        return input.map { line ->
            line.substringAfter("{")
                .substringBefore("}")
                .split(',')
                .associate { ratingInput ->
                    val (category, rating) = ratingInput.split('=')
                    category to rating.toLong()
                }
        }
    }

    private fun isAccepted(part: Map<String, Long>, workflows: Map<String, List<Rule>>): Boolean {
        var workflowName = "in"
        while (true) {
            println("wf: $workflowName")
            workflowName = workflows.getValue(workflowName)
                .first { rule ->
                    when (rule.operator) {
                        '<' -> part.getValue(rule.category) < rule.value
                        '>' -> part.getValue(rule.category) > rule.value
                        else -> true
                    }
                }
                .goto

            when (workflowName) {
                "A" -> return true
                "R" -> return false
            }
        }
    }

    private data class Rule(
        val category: String,
        val operator: Char?,
        val value: Long,
        val goto: String
    )
}