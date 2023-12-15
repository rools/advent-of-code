package year2023

object Day15 {
    fun solve1(input: List<String>): Int {
        return input[0].split(',')
            .sumOf { hash(it) }
    }

    fun solve2(input: List<String>): Int {
        val boxes = Array<MutableList<Lens>>(256) { mutableListOf() }

        val pattern = Regex("([a-z]+)([=-])(\\d)?")

        input[0].split(',').forEach { step ->
            val groups = pattern.matchEntire(step)!!.groupValues
            val label = groups[1]
            val operation = groups[2]
            val box = boxes[hash(label)]

            if (operation == "=") {
                val focalLength = groups[3].toInt()
                val currentIndex = box.indexOfFirst { it.label == label }
                if (currentIndex >= 0) {
                    box[currentIndex].focalLength = focalLength
                } else {
                    box += Lens(label, focalLength)
                }
            } else {
                box.removeIf { it.label == label }
            }
        }

        return boxes.withIndex().sumOf { (boxIndex, lenses) ->
            lenses.withIndex().sumOf { (lensIndex, lens) ->
                (boxIndex + 1) * (lensIndex + 1) * lens.focalLength
            }
        }
    }

    private fun hash(step: String): Int {
        return step.fold(0) { hash, char ->
            (hash + char.code) * 17 % 256
        }
    }

    data class Lens(val label: String, var focalLength: Int)
}