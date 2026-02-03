package files

import java.io.File

fun main() {
    val file = File("test.txt")
    file.writeText("")
    file.appendText("Welcome to Paradise\n")
    file.appendText("Nice Guys Finish Last\n")
    file.appendText("Minority\n")
    file.appendText("Wake Me Up When September Ends\n")

    val textContent = file.readText().trim()
    val splitStrings = textContent.split("\n")

    for ((idx, str) in splitStrings.withIndex()) {
        println("$idx - $str")
    }
}