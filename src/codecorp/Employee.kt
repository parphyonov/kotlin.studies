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
        if (this === other) return true
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

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + age
        result = 31 * result + salary
        result = 31 * result + name.hashCode()
        result = 31 * result + position.hashCode()
        return result
    }
}