package codecorp

import kotlin.random.Random

class Consultant(
    id: Int,
    name: String,
    age: Int = 0,
    salary: Int
): Employee(id, name, age, salary, Positions.CONSULTANT), Cleaner, Supplier {
    override fun copy(salary: Int): Consultant {
        return Consultant(this.id, this.name, this.age, salary)
    }

    fun greet() {
        print("Hi! My name is $name.")
        if (age > 0) print(" I am $age y.o.")
        println()
    }
    fun consultClients(suppressText: Boolean = true): Int {
        val numberOfConsultations = Random.nextInt(1, 20)

        if (!suppressText) {
            repeat(numberOfConsultations) {
                println("$name [${position.name}]: I have just consulted this client")
            }
        }

        return numberOfConsultations
    }

    override fun work() {
        println("I consult here...")
    }

    override fun clean() {
        println("[ID#$id]:>> ${name.lowercase()}@${position.name.lowercase()}.code: I clean")
    }

    override fun buyThings() {
        println("[ID#$id]:>> ${name.lowercase()}@${position.name.lowercase()}.code: I buy things")
    }
}