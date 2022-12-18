package year2022

object Day5 {
    fun solve1(input: List<String>): String {
        val stacks = Array(9) { mutableListOf<Char>() }

        var hasReadStacks = false

        input.forEach { line ->
            if (!hasReadStacks) {
                if (line.isBlank()) {
                    hasReadStacks = true
                    stacks.forEach { it.reverse() }
                } else {
                    stacks.forEachIndexed { index, stack ->
                        val item = line.getOrNull(1 + index * 4) ?: return@forEachIndexed
                        if (item.isLetter()) stack += item
                    }
                }
            } else {
                val parts = line.split(' ')
                val count = parts[1].toInt()
                val fromStack = parts[3].toInt() - 1
                val toStack = parts[5].toInt() - 1
                repeat(count) {
                    val item = stacks[fromStack].removeLast()
                    stacks[toStack].add(item)
                }
            }
        }

        return stacks.mapNotNull { it.lastOrNull() }
            .joinToString(separator = "")
    }

    fun solve2(input: List<String>): String {
        val stacks = Array(9) { mutableListOf<Char>() }

        var hasReadStacks = false

        input.forEach { line ->
            if (!hasReadStacks) {
                if (line.isBlank()) {
                    hasReadStacks = true
                    stacks.forEach { it.reverse() }
                } else {
                    stacks.forEachIndexed { index, stack ->
                        val item = line.getOrNull(1 + index * 4) ?: return@forEachIndexed
                        if (item.isLetter()) stack += item
                    }
                }
            } else {
                val parts = line.split(' ')
                val count = parts[1].toInt()
                val fromStack = parts[3].toInt() - 1
                val toStack = parts[5].toInt() - 1

                stacks[toStack].addAll(stacks[fromStack].takeLast(count))

                repeat(count) {
                    stacks[fromStack].removeLast()
                }
            }
        }

        return stacks.mapNotNull { it.lastOrNull() }
            .joinToString(separator = "")
    }
}