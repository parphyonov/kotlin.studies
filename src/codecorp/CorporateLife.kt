package codecorp

import codecorp.EmployeesRepository.findDirector

fun main() {
    val assistant = EmployeesRepository.findAssistant()
    assistant?.printInfo()

    val director = findDirector()
    director?.printInfo()
    director?.getCoffeeFrom(assistant)
}