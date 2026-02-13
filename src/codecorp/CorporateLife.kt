package codecorp

fun main() {
    val director = EmployeesRepository.findDirector() ?: throwDirectorIsRequired()
    director.printInfo()
}

fun throwDirectorIsRequired(): Nothing {
    throw IllegalStateException("Director is required for this program. Please, add it to the file employees.csv")
}