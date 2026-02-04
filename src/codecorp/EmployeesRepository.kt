package codecorp

import java.io.File

class EmployeesRepository {
    private val file = File("employees.csv")

    fun serialize(employee: Employee, toFile: File = file) {
        val id = employee.id
        val name = employee.name
        val age = employee.age
        val salary = employee.getSalary()
        val position = employee.position

        toFile.appendText("$id,$name,$age,$salary,$position\n")
    }

    fun checkEmployeeFileAndReturnEmployees(): MutableList<Employee> {
        if (!file.exists()) {
            println("Employees file doesn't exist. Add an item before proceeding")
            return mutableListOf()
        }

        val employees = loadAllEmployees()

        if (employees.isEmpty()) println("No employees created yet")

        return employees
    }

    fun registerNewEmployee(employee: Employee) {
        if (!file.exists()) file.createNewFile()
        serialize(employee)
    }

    fun changeSalary(id: Int, newSalary: Int) {
        val employees = loadAllEmployees()

        for (employee in employees)
            if (id == employee.id) {
                employee.setSalary(newSalary)
                break
            }

        rewrite(employees)
    }

    fun fireEmployee(id: Int) {
        val employees = loadAllEmployees()

        for (employee in employees)
            if (employee.id == id) {
                employees.remove(employee)
                break
            }

        rewrite(employees)
    }

    private fun loadAllEmployees(): MutableList<Employee> {
        val employees = mutableListOf<Employee>()

        val textContent = file.readText().trim()

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

    fun isUniqueIDInAList(employees: MutableList<Employee>, id: Int): Boolean {
        return employees.none { it.id == id }
    }

    private fun rewrite(employees: MutableList<Employee>) {
        file.writeText("")

        for (employee in employees) {
            serialize(employee)
        }
    }
}