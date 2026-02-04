package codecorp

import java.io.File

class EmployeesRepository {
    val employeesFile = File("employees.csv")

    fun serialize(employee: Employee, toFile: File = employeesFile) {
        val id = employee.id
        val name = employee.name
        val age = employee.age
        val salary = employee.getSalary()
        val position = employee.position

        toFile.appendText("$id,$name,$age,$salary,$position\n")
    }

    fun loadAllEmployees(): MutableList<Employee> {
        val employees = mutableListOf<Employee>()

        val textContent = employeesFile.readText().trim()

        if (textContent.isEmpty()) return employees

        val rawEmployees = textContent.split("\n")

        for (rawEmployee in rawEmployees) {
            val splitEmployee = rawEmployee.split(",")

            if (splitEmployee.size != 5) continue

            val (id, name, age, salary, position) = splitEmployee

            val employee = when (Positions.valueOf(position)) {
                Positions.DIRECTOR -> Director(id.toInt(), name, age.toInt(), salary.toInt())
                Positions.ASSISTANT -> Assistant(id.toInt(), name, age.toInt(), salary.toInt())
                Positions.CONSULTANT -> Consultant(id.toInt(), name, age.toInt(), salary.toInt())
                Positions.ACCOUNTANT -> Accountant(id.toInt(), name, age.toInt(), salary.toInt())
            }

            employees.add(employee)
        }

        return employees
    }
}