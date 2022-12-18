package year2022

object Day13 {
    fun solve1(input: List<String>): Int {
        var pos = 0
        var sum = 0
        var idx = 0
        while (pos < input.size) {
            val packet1 = parse(input[pos++])
            val packet2 = parse(input[pos++])
            val str1 = packet1.first.toString().replace(" ", "")
            if (str1 != input[pos - 2]) {
                throw IllegalStateException("$str1 ${input[pos - 2]}")
            }

            val res = compare(packet1.first, packet2.first)

            pos++
            idx++

            if (res == true) {
                sum += idx
            }
        }

        return sum
    }

    fun solve2(input: List<String>): Int {
        val dividerPackets = listOf(
            listOf(listOf(2)),
            listOf(listOf(6))
        )

        var packets = input.filter { it.isNotBlank() }
            .map { parse(it).first } + dividerPackets

        packets = packets.sortedWith { o1, o2 ->
            when (compare(o1, o2)) {
                true -> -1
                false -> 1
                else -> 0
            }
        }

        return (packets.indexOf(dividerPackets[0]) + 1) * (packets.indexOf(dividerPackets[1]) + 1)
    }

    private fun parse(str: String, pos: Int = 0): Pair<List<Any>, Int> {
        var p = pos + 1
        val list = mutableListOf<Any>()
        while (p < str.length) {
            when (str[p]) {
                '[' -> {
                    val res = parse(str, p)
                    list.add(res.first)
                    p += res.second
                }

                ',' -> {
                    p++
                    continue
                }

                ']' -> {
                    p++
                    break
                }

                else -> {
                    var i = 0
                    while (str[p].isDigit()) {
                        i = i * 10 + str[p].digitToInt()
                        p++
                    }
                    list += i
                }
            }
        }
        return list to p - pos
    }

    private fun compare(val1: Any, val2: Any): Boolean? {
        if (val1 is Int && val2 is Int) {
            return when {
                val1 < val2 -> true
                val1 > val2 -> false
                else -> null
            }
        }

        if (val1 !is List<*> && val1 !is Int) {
            throw IllegalStateException("$val1")
        }

        if (val1 is List<*> && val2 is List<*>) {
            var pos = 0
            while (true) {
                val item1 = val1.getOrNull(pos)
                val item2 = val2.getOrNull(pos)
                if (item1 == null && item2 == null) {
                    return null
                } else if (item1 == null) {
                    return true
                } else if (item2 == null) {
                    return false
                } else {
                    compare(item1, item2)?.let { return it }
                }
                pos++
            }
        } else if (val1 is List<*>) {
            return compare(val1, listOf(val2))
        } else {
            return compare(listOf(val1), val2)
        }
    }
}