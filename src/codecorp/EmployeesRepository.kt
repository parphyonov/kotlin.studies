package codecorp

import java.io.File

object EmployeesRepository {
    private val file = File("employees.csv")
    val employees = loadAllEmployees()

    private fun serialize(employee: Employee): String {
        val id = employee.id
        val name = employee.name
        val age = employee.age
        val salary = employee.getSalary()
        val position = employee.position

        return "$id,$name,$age,$salary,$position\n"
    }

    fun saveChanges() {
        if (!file.exists()) {
            file.createNewFile()
        }

        if (employees.isEmpty()) {
            println("No employees created yet")
        } else {
            val content = StringBuilder()

            for (employee in employees) {
                content.append(serialize(employee))
            }

            file.writeText(content.toString())
        }
    }

    fun registerNewEmployee(employee: Employee) {
        employees.add(employee)
    }

    fun changeSalary(id: Int, newSalary: Int) {
        for (employee in employees) {
            if (employee.id == id) {
                employee.setSalary(newSalary)
                break
            }
        }
    }

    fun fireEmployee(id: Int) {
        for (employee in employees) {
            if (employee.id == id) {
                employees.remove(employee)
                break
            }
        }
    }

    private fun loadAllEmployees(): MutableList<Employee> {
        println("EmployeesRepository: loaded all employees")

        val list = mutableListOf<Employee>()

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

            list.add(employee)
        }

        return list
    }

    fun isUniqueIDInAList(employees: MutableList<Employee>, id: Int): Boolean {
        return employees.none { it.id == id }
    }
}