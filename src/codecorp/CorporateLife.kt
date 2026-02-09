package codecorp

fun main() {
    val employees = EmployeesRepository.employees

//    println("CodeCorp <main>: Initiating Jarvis (Default Starter Accountant)")
//    val jarvis = Accountant(0, "Default Starter Accountant", salary = 0)

    for (employee in employees) {
        employee.work()
    }
}