package codecorp

class Director(
    id: Int,
    name: String,
    age: Int = 0,
    salary: Int
): Employee(id, name, age, salary, Positions.DIRECTOR), Supplier {
    override fun copy(salary: Int): Director {
        return Director(this.id, this.name, this.age, salary)
    }

    fun getCoffeeFrom(assistant: Assistant, coffeeType: String = "Cappuccino") {
        val coffeeDeFacto = assistant.bringCoffee(coffeeType)
        println("$name [${position.name}]: Thank you, ${assistant.name}! Your $coffeeDeFacto was delicious!")
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