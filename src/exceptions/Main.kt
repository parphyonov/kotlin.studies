package exceptions

fun main() {
    val list = listOf<Int>(1, 2, 3)

    try {
        val length = list.size + 55
        for (i in 0..length) {
            println(list[i])
        }
    } catch (e: IndexOutOfBoundsException) {
        println(e.message)
    }

    println("After try-catch")
}