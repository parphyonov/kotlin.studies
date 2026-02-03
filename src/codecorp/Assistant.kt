package codecorp

class Assistant (
    id: Int,
    name: String,
    age: Int = 0,
    salary: Int
): Employee(id, name, age, salary, Positions.ASSISTANT), Cleaner, Supplier {
    fun bringCoffee(coffeeType: String = "Cappuccino", count: Int = 1): String {
        repeat(count) {
            println("$name [${position.name}]: Get up")
            println("$name [${position.name}]: Go to the coffee machine")
            println("$name [${position.name}]: Press the \"$coffeeType\" button")
            println("$name [${position.name}]: Wait for the $coffeeType to be prepared")
            println("$name [${position.name}]: Take the cup")
            println("$name [${position.name}]: Bring it to the director")
            println("$name [${position.name}]: Put the cup upon the table")
            println("$name [${position.name}]: Return to your desk!")
        }

        return coffeeType
    }

    override fun work() {
        println("I assist here...")
    }

    override fun clean() {
        println("[ID#$id]:>> ${name.lowercase()}@${position.name.lowercase()}.code: I clean")
    }

    override fun buyThings() {
        println("[ID#$id]:>> ${name.lowercase()}@${position.name.lowercase()}.code: I buy things")
    }
}