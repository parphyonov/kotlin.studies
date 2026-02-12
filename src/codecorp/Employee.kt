package codecorp

abstract class Employee(
    open val id: Int,
    open val name: String,
    open val age: Int = 0,
    open val salary: Int = 15000,
    val position: Positions
) {
    abstract fun copy(
        id: Int = this.id,
        name: String = this.name,
        age: Int = this.age,
        salary: Int = this.salary,
        position: Positions = this.position
    ): Employee

    abstract fun work()

    fun printInfo() {
        println(this)
    }
}