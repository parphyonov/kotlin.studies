package codecorp

fun main() {
    val employees = EmployeesRepository.employees

//    println("CodeCorp <main>: Initiating Jarvis (Default Starter Accountant)")
//    val jarvis = Accountant(0, "Default Starter Accountant", salary = 0)

    val newcomer = Consultant(506, "Roman", 45, 90000)
    val newcomer01 = Consultant(507, "Andrew", 36, 900000)
    employees.add(newcomer)
    employees.add(newcomer01)

    for (employee in employees) {
        employee.work()
    }
}