package year2022

import java.util.*
import java.util.concurrent.atomic.AtomicInteger

object Day16 {
    private val regex = Regex("Valve ([A-Z]{2}) has flow rate=(\\d+); tunnels? leads? to valves? (.+)")

    fun solve1(input: List<String>): Any {
        val valves = mutableMapOf<String, Valve>()

        input.forEach { line ->
            val match = regex.matchEntire(line)!!
            val name = match.groupValues[1]
            valves[name] = Valve(
                name,
                match.groupValues[2].toInt(),
                match.groupValues[3].split(", ")
            )
        }

        val distances = mutableMapOf<Pair<String, String>, Int>()

        valves.values.forEach { v1 ->
            valves.values.forEach { v2 ->
                val distance = distance(valves, v1, v2)
                distances[v1.name to v2.name] = distance
                distances[v2.name to v1.name] = distance
            }
        }

        val maxFlow = valves.values.sumOf { valve ->
            valve.flowRate * 30
        }

        val minLoss = AtomicInteger(Int.MAX_VALUE)

        evaluate(
            valves.values.filter { it.flowRate > 0 },
            distances,
            valves.getValue("AA"),
            minLoss = minLoss
        )

        return maxFlow - minLoss.get()
    }

    fun solve2(input: List<String>): Any {
        val valves = mutableMapOf<String, Valve>()

        input.forEach { line ->
            val match = regex.matchEntire(line)!!
            val name = match.groupValues[1]
            valves[name] = Valve(
                name,
                match.groupValues[2].toInt(),
                match.groupValues[3].split(", ")
            )
        }

        val distances = mutableMapOf<Pair<String, String>, Int>()

        valves.values.forEach { v1 ->
            valves.values.forEach { v2 ->
                val distance = distance(valves, v1, v2)
                distances[v1.name to v2.name] = distance
                distances[v2.name to v1.name] = distance
            }
        }

        val maxFlow = valves.values.sumOf { valve ->
            valve.flowRate * 26
        }

        val minLoss = AtomicInteger(Int.MAX_VALUE)

        evaluate2(
            valves.values.filter { it.flowRate > 0 },
            distances,
            listOf(
                Character(valves.getValue("AA")),
                Character(valves.getValue("AA"))
            ),
            minLoss = minLoss
        )

        return maxFlow - minLoss.get()
    }

    private fun distance(
        valves: Map<String, Valve>,
        from: Valve,
        to: Valve
    ): Int {

        val visited = mutableSetOf<String>()
        val queue: Queue<Pair<Valve, Int>> = LinkedList()

        queue += from to 0

        while (queue.isNotEmpty()) {
            val v = queue.remove()
            if (v.first == to) {
                return v.second
            }

            visited += v.first.name

            v.first.tunnels.forEach { name ->
                if (name in visited) return@forEach
                queue += valves.getValue(name) to v.second + 1
            }
        }

        error("Not found")
    }

    private fun evaluate(
        allValves: List<Valve>,
        distances: Map<Pair<String, String>, Int>,
        startValve: Valve,
        currentLoss: Int = 0,
        currentTime: Int = 0,
        valves: List<Valve> = emptyList(),
        minLoss: AtomicInteger = AtomicInteger(Int.MAX_VALUE)
    ) {
        if (currentLoss >= minLoss.get()) {
            return
        }

        if (currentTime >= 30 || valves.size == allValves.size) {
            minLoss.set(currentLoss)
            return
        }

        allValves.forEach { valve ->
            if (valve in valves) return@forEach

            var time = distances.getValue(startValve.name to valve.name) + 1

            if (currentTime + time > 30) {
                time = 30 - currentTime
            }

            val loss = allValves.sumOf { v ->
                if (v in valves) return@sumOf 0
                v.flowRate * time
            }

            val newLoss = currentLoss + loss

            evaluate(
                allValves,
                distances,
                valve,
                newLoss,
                currentTime + time,
                valves + valve,
                minLoss
            )
        }
    }

    private fun evaluate2(
        allValves: List<Valve>,
        distances: Map<Pair<String, String>, Int>,
        characters: List<Character>,
        currentLoss: Int = 0,
        currentTime: Int = 0,
        valves: List<Valve> = emptyList(),
        minLoss: AtomicInteger = AtomicInteger(Int.MAX_VALUE)
    ) {
        if (currentLoss >= minLoss.get()) {
            return
        }

        if (currentTime >= 26 || valves.size == allValves.size) {
            minLoss.set(currentLoss)
            return
        }

        characters.forEachIndexed { cIndex, character ->
            if (character.timeLeft == 0) {
                allValves.forEach { valve ->
                    if (valve in valves) return@forEach
                    if (characters.any { it.targetValve == valve }) return@forEach

                    var time = distances.getValue(character.currentValve.name to valve.name) + 1

                    if (currentTime + time > 26) {
                        time = 26 - currentTime
                    }

                    val newCharacters = characters.toMutableList()
                    newCharacters[cIndex] = character.copy(
                        targetValve = valve,
                        timeLeft = time
                    )

                    evaluate2(
                        allValves,
                        distances,
                        newCharacters,
                        currentLoss,
                        currentTime,
                        valves,
                        minLoss
                    )
                }

                return
            }
        }

        val newTime = currentTime + 1
        val newValves = valves.toMutableList()

        val newLoss = currentLoss + allValves.sumOf { v ->
            if (v in valves) return@sumOf 0
            v.flowRate
        }

        val newCharacters = characters.map { character ->
            if (character.timeLeft == 1) {
                newValves += character.targetValve!!
                character.copy(
                    currentValve = character.targetValve!!,
                    targetValve = null,
                    timeLeft = 0
                )
            } else {
                character.copy(timeLeft = character.timeLeft - 1)
            }
        }

        evaluate2(
            allValves,
            distances,
            newCharacters,
            newLoss,
            newTime,
            newValves,
            minLoss
        )
    }

    private data class Valve(
        val name: String,
        var flowRate: Int,
        val tunnels: List<String>
    )

    private data class Character(
        val currentValve: Valve,
        val targetValve: Valve? = null,
        val timeLeft: Int = 0
    )
}