import java.io.FileNotFoundException

open class DayTest(private val year: Int, private val day: Int) {
    protected fun input(name: String): List<String> {
        val path = "/$year/$day/$name.txt"

        val url = javaClass.getResource(path)
            ?: throw FileNotFoundException("Could not open $path")

        return url.openStream()
            .bufferedReader()
            .use { it.readLines() }
    }
}