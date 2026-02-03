package codecorp

import java.io.File

abstract class Employee(
    val id: Int,
    val name: String,
    val age: Int = 0,
    val position: Positions
) {
    var salary: Int = 15000

    abstract fun work()

    override fun toString(): String {
        val localPosition = position.name.lowercase()
//        val undefiniteArticleForm = if (localPosition[0] !in "aeiouy") "a" else "an"

        return "$name.$id@code.$localPosition [ Age: $age | Salary: $salary ]"
    }

    open fun serializeTo(file: File) {
        file.appendText("$id%$name%$age%$salary%$position\n")
    }
}