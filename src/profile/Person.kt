package profile

class Person(
    val name: String,
    val age: Int,
    val height: Int,
    val weight: Double
) {
    val runTimes = 10

    fun sayHello() {
        println("– Hello! My name is $name")
    }

    fun run() {
        repeat(runTimes) {
            println("– I'm running! – says $name with a heavy breath…")
        }
    }

    fun copy(
        name: String = this.name,
        age: Int = this.age,
        height: Int = this.height,
        weight: Double = this.weight
    ): Person = Person(name, age, height, weight)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Person) return false
        return this.name == other.name &&
                this.age == other.age &&
                this.height == other.height &&
                this.weight == other.weight
    }

    override fun toString(): String {
        return "[ Name: $name | Age: $age | Height: $height | Weight: $weight ]"
    }

    override fun hashCode(): Int {
        var result = age
        result = 31 * result + height
        result = 31 * result + weight.hashCode()
        result = 31 * result + runTimes
        result = 31 * result + name.hashCode()
        return result
    }
}

fun main() {
    val p1 = Person("Vektor", 50, 200, 120.0)
    val p2 = Person("Max", 20, 175, 84.3)
    val p3 = Person("Aliya", 41, 167, 77.1)
    val p4 = Person("Lee", 38, 178, 108.9)
    val p5 = Person("Lee", 38, 178, 108.9)
    val p6 = Person("Max", 42, 167, 77.1)

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
