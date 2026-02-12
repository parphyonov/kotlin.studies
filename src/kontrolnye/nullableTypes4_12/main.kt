package kontrolnye.nullableTypes4_12

data class UserData(
    val name: String?,
    val email: String?,
    val age: Int?
)

interface Repository {
    fun getUsers(): List<UserData?>
}

object UsersRepository: Repository {
    var userList = mutableListOf<UserData?>()

    override fun getUsers(): List<UserData?> {
        return userList.toList()
    }

    fun setUsers(lst: List<UserData?>) {
        this.userList = lst.toMutableList()
    }

}

class UserViewModel(private val repository: Repository) {

    /**
     * Реализуйте метод getUserDescriptions, который:
     * 1. Получает данные из репозитория.
     * 2. Удаляет null элементы списка.
     * 3. Заменяет null значения в полях name, email и age на дефолтные.
     *
     * @return Список строк с описаниями пользователей.
     */
    fun getUserDescriptions(): List<String> {
        val userData = repository.getUsers()
        val res = mutableListOf<String>()

        for (data in userData) {
            if (data != null) {
                val name = data.name ?: "Unknown Name"
                val email = data.email ?: "Unknown Email"
                val age = data.age ?: 0

                res.add("Name: $name, Email: $email, Age: $age")
            }
        }

        return res.toList()
    }
}

fun main() {
    val data = listOf(
        UserData("Alice", "alice@example.com", 30),
        null,
        UserData(null, "guest@example.com", 25),
        UserData("Bob", null, null),
        UserData(null, null, null),
        UserData("Charlie", "charlie@example.com", 40),
        UserData(null, "no-email@example.com", 10),
        UserData("Diana", "diana@example.com", 0),
        UserData("Eve", null, 35),
        null
    )
    val repo = UsersRepository
    repo.setUsers(data)

    val uvm = UserViewModel(repo)
    val userDescriptions = uvm.getUserDescriptions()

    for (description in userDescriptions)
        println(description)
}