package codecorp

data class Director(
    override val id: Int,
    override val name: String,
    override val age: Int = 0,
    override val salary: Int
): Employee(id, name, age, salary, Positions.DIRECTOR), Supplier {
    override fun copy(
        id: Int,
        name: String,
        age: Int,
        salary: Int,
        position: Positions
    ): Director = Director(id, name, age, salary)

    fun getCoffeeFrom(assistant: Assistant?, coffeeType: String = "Cappuccino") {
        val coffeeDeFacto = assistant?.bringCoffee(coffeeType)
        if (assistant != null && coffeeDeFacto != null) {
            println("$name [${position.name}]: Thank you, ${assistant.name}! Your $coffeeDeFacto was delicious!")
        } else {
            println("There is either no assistant or coffee")
        }
    }

    fun getCleanerToCleanUp(cleaner: Cleaner) {
        cleaner.clean()
    }

    fun getConsultantToWork(consultant: Consultant) {
        val numberOfConsultations = consultant.consultClients()
        println("${consultant.name} [${consultant.position.name}]: I have consulted $numberOfConsultations clients today!")
    }

    override fun work() {
        println("I direct here...")
    }

    override fun buyThings() {
        println("[ID#$id]:>> ${name.lowercase()}@${position.name.lowercase()}.code: I buy things")
    }
}