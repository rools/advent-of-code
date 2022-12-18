package year2022

object Day18 {
    private val sides = listOf(
        Cube(1, 0, 0),
        Cube(-1, 0, 0),
        Cube(0, 1, 0),
        Cube(0, -1, 0),
        Cube(0, 0, 1),
        Cube(0, 0, -1),
    )

    fun solve1(input: List<String>): Int {
        return calculateArea(input, excludeInterior = false)
    }

    fun solve2(input: List<String>): Int {
        return calculateArea(input, excludeInterior = true)
    }

    private fun calculateArea(input: List<String>, excludeInterior: Boolean): Int {
        val droplet = input.map { line ->
            val parts = line.split(",")
            Cube(
                x = parts[0].toInt(),
                y = parts[1].toInt(),
                z = parts[2].toInt()
            )
        }.toSet()

        val exteriorCubes = if (excludeInterior) {
            getExteriorCubes(droplet)
        } else {
            emptySet()
        }

        return droplet.sumOf { cube ->
            sides.count { side ->
                val neighbor = cube + side
                neighbor !in droplet && (!excludeInterior || neighbor in exteriorCubes)
            }
        }
    }

    private fun getExteriorCubes(droplet: Set<Cube>): Set<Cube> {
        val min = Cube(
            x = droplet.minOf { it.x } - 1,
            y = droplet.minOf { it.y } - 1,
            z = droplet.minOf { it.z } - 1
        )

        val max = Cube(
            x = droplet.maxOf { it.x } + 1,
            y = droplet.maxOf { it.y } + 1,
            z = droplet.maxOf { it.z } + 1
        )

        val exteriorCubes = mutableSetOf<Cube>()

        fun markExterior(cube: Cube) {
            exteriorCubes += cube

            sides.forEach { side ->
                val neighbor = cube + side
                if (neighbor in exteriorCubes || neighbor in droplet) return@forEach
                if (neighbor.x < min.x || neighbor.y < min.y || neighbor.z < min.z) return@forEach
                if (neighbor.x > max.x || neighbor.y > max.y || neighbor.z > max.z) return@forEach
                markExterior(neighbor)
            }
        }

        markExterior(min)

        return exteriorCubes
    }

    private data class Cube(
        val x: Int,
        val y: Int,
        val z: Int
    ) {
        operator fun plus(cube: Cube) = Cube(
            x + cube.x,
            y + cube.y,
            z + cube.z
        )
    }
}