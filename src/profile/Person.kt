package profile

class Person {
    val name: String
    val age: Int
    val height: Int
    val weight: Double

    constructor(name: String, age: Int, height: Int, weight: Double) {
        this.name = name
        this.age = age
        this.height = height
        this.weight = weight
    }

    val runTimes = 10


    fun sayHello() {
        println("– Hello! My name is $name")
    }


    fun readHRMetrics() {
        println("[ Name: $name | Age: $age | Height: $height | Weight: $weight ]")
    }


    fun run() {
        repeat(runTimes) {
            println("– I'm running! – says $name with a heavy breath…")
        }
    }
}
