package codecorp

fun main() {
    val employees = EmployeesRepository.employees

    for (employee in employees) {
        employee.work()
    }
}