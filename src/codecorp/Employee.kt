package codecorp

abstract class Employee(
    val id: Int,
    val name: String,
    val age: Int = 0,
    val salary: Int = 15000,
    val position: Positions
) {
    abstract fun copy(age: Int = this.age, salary: Int = this.salary): Employee

    abstract fun work()

    override fun equals(other: Any?): Boolean {
        if (other !is Employee) return false

        return id == other.id &&
            name == other.name &&
            age == other.age &&
            salary == other.salary &&
            position == other.position
    }

    override fun toString(): String {
        val localPosition = position.name.lowercase()

        return "$name.$id@code.$localPosition [ Age: $age | Salary: $salary ]"
    }
}