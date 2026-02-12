package codecorp

fun main() {
    val assistant = EmployeesRepository.findAssistant()
    assistant?.printInfo()

    val director = EmployeesRepository.findDirector()
    director?.printInfo()

    val assistantSalary = assistant?.salary ?: 0
    val directorSalary = director?.salary ?: 0
    val total = assistantSalary + directorSalary

    println("Total salary: $total")
}