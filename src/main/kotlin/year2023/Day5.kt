package year2023

import lib.algos.split

object Day5 {
    fun solve1(input: List<String>): Long {
        val almanac = parseAlmanac(input)

        return almanac.seeds.minOf { seed ->
            var source = seed
            almanac.maps.forEach { ranges ->
                source = getDestination(ranges, source)
            }
            source
        }
    }

    fun solve2(input: List<String>): Long {
        val almanac = parseAlmanac(input)

        val seedRanges = almanac.seeds
            .chunked(2)
            .map { it[0] until it[0] + it[1] }

        var location = 0L
        while (true) {
            var destination = location
            almanac.maps.reversed().forEach { ranges ->
                destination = getSource(ranges, destination)
            }
            if (seedRanges.any { destination in it }) {
                return location
            }
            location++
        }
    }

    private fun parseAlmanac(input: List<String>): Almanac {
        return Almanac(
            seeds = input[0]
                .substringAfter(": ")
                .split(' ')
                .map { it.toLong() },
            maps = input.split("")
                .drop(1)
                .map { list ->
                    list.drop(1).map { line ->
                        val parts = line.split(' ')
                            .map { it.toLong() }
                        MapRange(parts[0], parts[1], parts[2])
                    }
                }
        )
    }

    private fun getDestination(ranges: List<MapRange>, source: Long): Long {
        ranges.forEach { range ->
            if (source in range.sourceStart until (range.sourceStart + range.count)) {
                return range.destinationStart + (source - range.sourceStart)
            }
        }
        return source
    }

    private fun getSource(ranges: List<MapRange>, destination: Long): Long {
        ranges.forEach { range ->
            if (destination in range.destinationStart until (range.destinationStart + range.count)) {
                return range.sourceStart + (destination - range.destinationStart)
            }
        }
        return destination
    }

    data class Almanac(
        val seeds: List<Long>,
        val maps: List<List<MapRange>>
    )

    data class MapRange(
        val destinationStart: Long,
        val sourceStart: Long,
        val count: Long
    )
}