package kontrolnye

import kotlin.system.measureTimeMillis

class TextMerger {
    fun mergeText(lines: List<String>): String {
        val result = StringBuilder()

        for (line in lines) {
            result.append("$line\n")
        }

        return result.toString()
    }
}

fun main() {
    val testLines = List(10000) { "Line $it " }
    val merger = TextMerger()

    val time = measureTimeMillis {
        val result = merger.mergeText(testLines)
        println("Length: ${result.length}")
    }

    println("Time: $time ms")
}