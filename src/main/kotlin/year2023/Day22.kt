package year2023

import lib.data.Vec3
import lib.utils.minus
import lib.utils.plus
import java.util.LinkedList
import java.util.Queue

object Day22 {
    fun solve1(input: List<String>): Int {
        val bricks = parseBricks(input)
        settleBricks(bricks)
        return bricks.count { countFallingBricks(it) == 0 }
    }

    fun solve2(input: List<String>): Int {
        val bricks = parseBricks(input)
        settleBricks(bricks)
        return bricks.sumOf { countFallingBricks(it) }
    }

    private fun settleBricks(bricks: List<Brick>) {
        val gravity = Vec3(x = 0, y = 0, z = -1)

        bricks.sortedBy { b -> b.cubes.minOf { it.z } }.forEach { brick ->
            while (true) {
                if (brick.cubes.any { it.z <= 0 }) break

                val collidingBricks = bricks.filter { it.id != brick.id && it.intersectsWith(brick) }

                collidingBricks.forEach { collidingBrick ->
                    brick.supportedBy += collidingBrick
                    collidingBrick.supporting += brick
                }

                if (collidingBricks.isNotEmpty()) break

                brick.cubes.replaceAll { it + gravity }
            }

            brick.cubes.replaceAll { it - gravity }
        }
    }

    private fun countFallingBricks(brickToRemove: Brick): Int {
        val removedBricks = mutableSetOf<Int>()

        val queue: Queue<Brick> = LinkedList()
        queue += brickToRemove

        while (queue.isNotEmpty()) {
            val brick = queue.remove()

            if (brick != brickToRemove && (brick.supportedBy.map { it.id } - removedBricks).isNotEmpty()) {
                continue
            }

            removedBricks += brick.id
            brick.supporting.forEach { queue += it }
        }

        return (removedBricks).size - 1
    }

    private fun parseBricks(input: List<String>): List<Brick> {
        return input.mapIndexed { index, line ->
            val parts = line.split('~', ',')
                .map { it.toInt() }
            val cubes = mutableListOf<Vec3>().apply {
                for (x in parts[0]..parts[3]) {
                    for (y in parts[1]..parts[4]) {
                        for (z in parts[2]..parts[5]) {
                            add(Vec3(x, y, z))
                        }
                    }
                }
            }
            Brick(id = index, cubes = cubes)
        }
    }

    private data class Brick(
        val id: Int,
        val cubes: MutableList<Vec3>,
        val supporting: MutableList<Brick> = mutableListOf(),
        val supportedBy: MutableList<Brick> = mutableListOf()
    )

    private fun Brick.intersectsWith(brick: Brick): Boolean {
        return cubes.any { it in brick.cubes }
    }
}