package codecorp

abstract class Employee(
    val id: Int,
    val name: String,
    val age: Int = 0,
    private var salary: Int = 15000,
    val position: Positions
) {
    fun setSalary(salary: Int) = if (salary < this.salary) {
        println("You may only increase salary. Operation aborted")
    } else {
        this.salary = salary
    }

    fun getSalary(): Int = this.salary

    abstract fun work()

    override fun toString(): String {
        val localPosition = position.name.lowercase()

        return "$name.$id@code.$localPosition [ Age: $age | Salary: $salary ]"
    }
}