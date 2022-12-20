package year2022

import kotlin.math.max

object Day19 {
    private const val ORE = 0
    private const val CLAY = 1
    private const val OBSIDIAN = 2
    private const val GEODE = 3

    fun solve1(input: List<String>): Int {
        val blueprints = parseBlueprints(input)

        return blueprints.sumOf { blueprint ->
            findMaxGeodes(blueprint, timeLimit = 24) * blueprint.id
        }
    }

    fun solve2(input: List<String>): Int {
        val blueprints = parseBlueprints(input)

        var product = 1
        blueprints.take(3)
            .forEach { product *= findMaxGeodes(it, timeLimit = 32) }

        return product
    }

    private fun findMaxGeodes(
        blueprint: Blueprint,
        timeLimit: Int
    ): Int {
        var maxGeodes = 0
        val bestScores = IntArray(timeLimit + 1)

        fun findMaxGeodes(
            robots: IntArray,
            resources: IntArray,
            time: Int,
            score: Int,
            build: Int
        ) {
            if (score > bestScores[time]) {
                bestScores[time] = score
            }

            if (score < bestScores[max(0, time - 4)]) {
                return
            }

            if (time == timeLimit) {
                maxGeodes = max(maxGeodes, resources[GEODE])
                return
            }

            val newRobots = robots.copyOf()
            if (build >= 0) {
                newRobots[build]++
            }

            val newResources = resources.copyOf()
            repeat(4) { resource ->
                newResources[resource] += newRobots[resource]
            }

            repeat(4) { resource ->
                val cost = blueprint.robotCosts[resource]
                if (canAfford(resources, cost)) {
                    val newResources2 = newResources.copyOf()
                    subtract(newResources2, cost)
                    findMaxGeodes(
                        newRobots,
                        newResources2,
                        time + 1,
                        score + blueprint.values[resource] * (timeLimit - time),
                        resource
                    )
                }
            }

            findMaxGeodes(
                newRobots,
                newResources,
                time + 1,
                score,
                -1
            )
        }

        findMaxGeodes(
            intArrayOf(1, 0, 0, 0),
            IntArray(4),
            0,
            timeLimit,
            -1
        )

        return maxGeodes
    }

    private fun canAfford(resources: IntArray, cost: IntArray): Boolean {
        return 0.until(4).all { resource ->
            resources[resource] >= cost[resource]
        }
    }

    private fun subtract(resources: IntArray, cost: IntArray) {
        repeat(4) { resource ->
            resources[resource] -= cost[resource]
        }
    }

    private fun parseBlueprints(input: List<String>): List<Blueprint> {
        val regex = Regex(
            "Blueprint (\\d+): Each ore robot costs (\\d+) ore." +
                    " Each clay robot costs (\\d+) ore." +
                    " Each obsidian robot costs (\\d+) ore and (\\d+) clay." +
                    " Each geode robot costs (\\d+) ore and (\\d+) obsidian."
        )
        return input.map { line ->
            val match = regex.matchEntire(line)!!
            val blueprint = Blueprint(
                id = match.groupValues[1].toInt(),
                robotCosts = arrayOf(
                    intArrayOf(match.groupValues[2].toInt(), 0, 0, 0),
                    intArrayOf(match.groupValues[3].toInt(), 0, 0, 0),
                    intArrayOf(match.groupValues[4].toInt(), match.groupValues[5].toInt(), 0, 0),
                    intArrayOf(match.groupValues[6].toInt(), 0, match.groupValues[7].toInt(), 0),
                )
            )
            calculateValues(blueprint)
            blueprint
        }
    }

    private fun calculateValues(blueprint: Blueprint) {
        val clayValue = blueprint.robotCosts[CLAY][ORE]
        val obsidianValue = blueprint.robotCosts[OBSIDIAN].run {
            get(ORE) + get(CLAY) * clayValue
        }
        val geodeValue = blueprint.robotCosts[GEODE].run {
            get(ORE) + get(OBSIDIAN) * obsidianValue
        }
        blueprint.values[ORE] = 1
        blueprint.values[CLAY] = clayValue
        blueprint.values[OBSIDIAN] = obsidianValue
        blueprint.values[GEODE] = geodeValue
    }

    private data class Blueprint(
        val id: Int,
        val robotCosts: Array<IntArray>,
        val values: IntArray = IntArray(4)
    ) {
        override fun equals(other: Any?): Boolean = (other is Blueprint && other.id == id)
        override fun hashCode(): Int = id
    }
}