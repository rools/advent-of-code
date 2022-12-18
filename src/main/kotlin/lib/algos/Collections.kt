package lib.algos

fun <T> Iterable<T>.split(delimiter: T): List<List<T>> {
    val chunks = mutableListOf<List<T>>()
    var items = mutableListOf<T>()
    forEach { item ->
        if (item == delimiter) {
            chunks += items
            items = mutableListOf()
        } else {
            items += item
        }
    }
    chunks += items
    return chunks
}