package codecorp

abstract class Employee(
    open val id: Int,
    open val name: String,
    open val age: Int = 0,
    open val salary: Int = 15000,
    val position: Positions
) {
    abstract fun copy(age: Int = this.age, salary: Int = this.salary): Employee

    abstract fun work()
}