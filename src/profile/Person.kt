package profile

data class Person(
    val name: String,
    val fullName: String,
    val height: Int,
    val weight: Double
) {
    val runTimes = 10
    var age: Int = 0
        get() = field
        set(value) {
            field = if (value < age) field else value
        }

    fun sayHello() {
        println("– Hello! My name is $name")
    }

    fun run() {
        repeat(runTimes) {
            println("– I'm running! – says $name with a heavy breath…")
        }
    }
}

fun main() {
    val p1 = Person("Vektor", "McFly", 200, 120.0)
    val p2 = Person("Max", "Haim", 175, 84.3)
    val p3 = Person("Aliya", "Baliya", 167, 77.1)
    val p4 = Person("Lee", "Strauss", 178, 108.9)
    val p5 = Person("Lee", "Strauss", 178, 108.9)
    val p6 = Person("Max", "Haim", 167, 77.1)

    val shift = mutableSetOf(p1, p2, p3, p4, p5, p6)
    shift.add(p2.copy())
    shift.add(p2.copy(name = "Marat"))

    for (p in shift.iterator()) {
        println(p)
    }

    println(p4 == p5)
    println(p4 === p5)
    println(p6 == p3)
}
