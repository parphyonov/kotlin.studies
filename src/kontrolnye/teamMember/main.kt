package kontrolnye.teamMember

// Базовый абстрактный класс для сотрудников
abstract class TeamMember(
    open val id: Int,
    open val name: String,
    open val role: String
)

// Класс разработчика
data class Developer(
    override val id: Int,
    override val name: String,
    override val role: String,
    val primaryLanguage: String
) : TeamMember(id, name, role)

// Класс менеджера проекта
data class ProjectManager(
    override val id: Int,
    override val name: String,
    override val role: String,
    val projectsHandled: Int
) : TeamMember(id, name, role)

// Класс проекта
data class Project(
    val projectId: Int,
    val projectName: String,
    val client: String,
    val budget: Double,
    val durationMonths: Int
)

// Класс задачи
data class Task(
    val taskId: Int,
    val title: String,
    val description: String,
    val assignee: TeamMember,
    val priority: Int,
    val deadline: String
)

// Класс для проверки работы всех методов в классах
fun simulateWorkflow() {
    val dev = Developer(1, "Alice", "Developer", "Kotlin")
    val pm = ProjectManager(2, "Bob", "Manager", 5)
    val project = Project(101, "Project A", "ClientX", 50000.0, 12)
    val task = Task(201, "Implement Feature", "Feature implementation for Project A", dev, 1, "2023-12-31")

    // Проверка наследования
    // Тест 1: Developer является экземпляром TeamMember
    if (dev !is TeamMember) {
        throw Exception("Test 1 failed: Developer не является экземпляром TeamMember")
    }

    // Тест 2: ProjectManager является экземпляром TeamMember
    if (pm !is TeamMember) {
        throw Exception("Test 2 failed: ProjectManager не является экземпляром TeamMember")
    }

    // Тесты toString()
    // Тест 3: Проверка toString() для Developer
    if (dev.toString() != "Developer(id=1, name=Alice, role=Developer, primaryLanguage=Kotlin)") {
        throw Exception("Test 3 failed: Developer.toString() вернул некорректное значение")
    }

    // Тест 4: Проверка toString() для ProjectManager
    if (pm.toString() != "ProjectManager(id=2, name=Bob, role=Manager, projectsHandled=5)") {
        throw Exception("Test 4 failed: ProjectManager.toString() вернул некорректное значение")
    }

    // Тест 5: Проверка toString() для Project
    if (project.toString() != "Project(projectId=101, projectName=Project A, client=ClientX, budget=50000.0, durationMonths=12)") {
        throw Exception("Test 5 failed: Project.toString() вернул некорректное значение")
    }

    // Тест 6: Проверка toString() для Task
    if (task.toString() != "Task(taskId=201, title=Implement Feature, description=Feature implementation for Project A, assignee=Developer(id=1, name=Alice, role=Developer, primaryLanguage=Kotlin), priority=1, deadline=2023-12-31)") {
        throw Exception("Test 6 failed: Task.toString() вернул некорректное значение")
    }

    // Тесты equals() и hashCode()
    val devCopy = dev.copy()
    val pmCopy = pm.copy()

    // Тест 7: Проверка equals() для Developer
    if (dev != devCopy) {
        throw Exception("Test 7 failed: Developer.equals() вернул false для эквивалентных объектов")
    }

    // Тест 8: Проверка hashCode() для Developer
    if (dev.hashCode() != devCopy.hashCode()) {
        throw Exception("Test 8 failed: Developer.hashCode() вернул разные значения для эквивалентных объектов")
    }

    // Тест 9: Проверка equals() для ProjectManager
    if (pm != pmCopy) {
        throw Exception("Test 9 failed: ProjectManager.equals() вернул false для эквивалентных объектов")
    }

    // Тест 10: Проверка hashCode() для ProjectManager
    if (pm.hashCode() != pmCopy.hashCode()) {
        throw Exception("Test 10 failed: ProjectManager.hashCode() вернул разные значения для эквивалентных объектов")
    }

    // Тест 11: Проверка equals() для разных объектов Developer
    val differentDev = Developer(3, "Eve", "Developer", "Python")
    if (dev == differentDev) {
        throw Exception("Test 11 failed: Developer.equals() вернул true для различных объектов")
    }

    // Тест 12: Проверка equals() для разных объектов ProjectManager
    val differentPM = ProjectManager(4, "Charlie", "Manager", 7)
    if (pm == differentPM) {
        throw Exception("Test 12 failed: ProjectManager.equals() вернул true для различных объектов")
    }

    // Тесты copy()
    // Тест 13: Проверка copy() без изменений для Developer
    val identicalDev = dev.copy()
    if (identicalDev != dev) {
        throw Exception("Test 13 failed: Developer.copy() создал объект, не равный оригиналу")
    }

    // Тест 14: Проверка copy() с изменением primaryLanguage для Developer
    val updatedDev = dev.copy(primaryLanguage = "Java")
    if (updatedDev.primaryLanguage != "Java" || updatedDev == dev) {
        throw Exception("Test 14 failed: Developer.copy() с изменением primaryLanguage не дал ожидаемых результатов")
    }

    // Тест 15: Проверка copy() без изменений для ProjectManager
    val identicalPM = pm.copy()
    if (identicalPM != pm) {
        throw Exception("Test 15 failed: ProjectManager.copy() создал объект, не равный оригиналу")
    }

    // Тест 16: Проверка copy() с изменением projectsHandled для ProjectManager
    val updatedPM = pm.copy(projectsHandled = 10)
    if (updatedPM.projectsHandled != 10 || updatedPM == pm) {
        throw Exception("Test 16 failed: ProjectManager.copy() с изменением projectsHandled не дал ожидаемых результатов")
    }

    // Тест 17: Проверка copy() без изменений для Project
    val identicalProject = project.copy()
    if (identicalProject != project) {
        throw Exception("Test 17 failed: Project.copy() создал объект, не равный оригиналу")
    }

    // Тест 18: Проверка copy() с изменением бюджета для Project
    val updatedProject = project.copy(budget = 60000.0)
    if (updatedProject.budget != 60000.0 || updatedProject == project) {
        throw Exception("Test 18 failed: Project.copy() с изменением budget не дал ожидаемых результатов")
    }

    // Тест 19: Проверка equals() для Project с разными данными
    val differentProject = Project(102, "Project B", "ClientY", 75000.0, 18)
    if (project == differentProject) {
        throw Exception("Test 19 failed: Project.equals() вернул true для различных объектов")
    }

    // Тест 20: Проверка equals() для Task
    val taskCopy = task.copy()
    if (task != taskCopy) {
        throw Exception("Test 20 failed: Task.equals() вернул false для эквивалентных объектов")
    }

    // Тест 21: Проверка copy() с изменением assignee для Task
    val newAssigneeTask = task.copy(assignee = pm)
    if (newAssigneeTask.assignee != pm || newAssigneeTask == task) {
        throw Exception("Test 21 failed: Task.copy() с изменением assignee не дал ожидаемых результатов")
    }

    // Тест 22: Проверка equals() для Task с разными данными
    val differentTask = Task(202, "Review Code", "Code review for feature", pm, 2, "2023-11-30")
    if (task == differentTask) {
        throw Exception("Test 22 failed: Task.equals() вернул true для различных объектов")
    }

    println("Все тесты пройдены успешно!")
}

fun main() {
    simulateWorkflow()
}