package year2022

object Day7 {
    fun solve1(input: List<String>): Int {
        val rootDir = getFiles(input)
        return rootDir.sum()
    }

    fun solve2(input: List<String>): Int {
        val rootDir = getFiles(input)
        val list = mutableListOf<File>()
        rootDir.listFiles(list)

        val free = 70000000 - rootDir.size

        val neededSpace = 30000000 - free
        list.sortBy { it.size }

        return list.first { it.files.isNotEmpty() && it.size >= neededSpace }.size
    }

    private fun getFiles(input: List<String>): File {
        val rootDir = File(
            name = "/",
            size = 0,
            parent = null
        )

        var currentDir = rootDir

        input.drop(1).forEach { line ->
            val parts = line.split(' ')

            if (parts[0] == "$") {
                if (parts[1] == "cd") {
                    currentDir = if (parts[2] == "..") {
                        currentDir.parent!!
                    } else {
                        currentDir.files.find { it.name == parts[2] }!!
                    }
                }
            } else {
                if (parts[0] == "dir") {
                    currentDir.files += File(
                        name = parts[1],
                        parent = currentDir,
                        size = 0
                    )
                } else {
                    currentDir.files += File(
                        name = parts[1],
                        parent = currentDir,
                        size = parts[0].toInt()
                    )
                }
            }
        }

        rootDir.populateSizes()

        return rootDir
    }

    private fun File.populateSizes(): Int {
        files.forEach { file ->
            size += if (file.files.isEmpty()) {
                file.size
            } else {
                file.populateSizes()
            }
        }
        return size
    }

    private fun File.sum(): Int {
        return files.sumOf { file ->
            file.sum() + if (file.files.isEmpty() || file.size > 100000) {
                0
            } else {
                file.size
            }
        }
    }

    private fun File.listFiles(list: MutableList<File>) {
        list += this
        files.forEach { it.listFiles(list) }
    }

    private data class File(
        val name: String,
        var size: Int,
        var parent: File?,
        val files: MutableList<File> = mutableListOf()
    )
}

