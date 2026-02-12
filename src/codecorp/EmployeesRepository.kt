package codecorp

import java.io.File

object EmployeesRepository {
    private val file = File("employees.csv")

    private var _employees = loadAllEmployees()
    val employees
        get() = _employees.toList()

    private fun serialize(employee: Employee): String {
        val id = employee.id
        val name = employee.name
        val age = employee.age
        val salary = employee.salary
        val position = employee.position

        return "$id,$name,$age,$salary,$position\n"
    }

    fun findAssistant(): Assistant? {
        for (employee in _employees)
            if (employee is Assistant)
                return employee

        return null
    }

    fun findDirector(): Director? {
        for (employee in _employees)
            if (employee is Director)
                return employee

        return null
    }

    fun saveChanges() {
        if (!file.exists()) {
            file.createNewFile()
        }

        if (_employees.isEmpty()) {
            println("No employees created yet")
        } else {
            val content = StringBuilder()

            for (employee in _employees) {
                content.append(serialize(employee))
            }

            file.writeText(content.toString())
        }
    }

    fun registerNewEmployee(newEmployee: Employee) {
        _employees.add(newEmployee)
    }

    fun changeAge(id: Int, newAge: Int) {
        for (employee in _employees) {
            if (employee.id == id) {
                if (employee.age > newAge) return

                _employees.remove(employee)
                _employees.add(employee.copy(age = newAge))
                break
            }
        }
    }

    fun changeSalary(id: Int, newSalary: Int) {
        for (employee in _employees) {
            if (employee.id == id) {
                if (employee.salary > newSalary) return

                _employees.remove(employee)
                _employees.add(employee.copy(salary = newSalary))
                break
            }
        }
    }

    fun fireEmployee(id: Int) {
        for (employee in _employees) {
            if (employee.id == id) {
                _employees.remove(employee)
                break
            }
        }
    }

    private fun loadAllEmployees(): MutableSet<Employee> {
        println("EmployeesRepository: loaded all employees")

        val list = mutableSetOf<Employee>()

        val textContent = file.readText().trim()

        if (textContent.isEmpty()) return _employees

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

//    fun isUniqueIDInAList(list: List<Employee>, id: Int): Boolean {
//        return list.none { it.id == id }
//    }
}