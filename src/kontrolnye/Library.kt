package kontrolnye

data class Book(val title: String, val author: String)

data class User1(val name: String, val id: Int)

class Library(val libraryName: String) {
    private val _books = mutableListOf<Book>()
    val books
        get() = _books.toList()

    private val _users = mutableListOf<User1>()
    val users
        get() = _users.toList()

    fun addBook(book: Book) {
        _books.add(book)
        println("Книга \"${book.title}\" добавлена в библиотеку.")
    }

    fun addUser(user: User1) {
        _users.add(user)
        println("Пользователь ${user.name} добавлен в библиотеку.")
    }

    fun removeBookByTitle(title: String): Boolean {
        val removed = _books.removeIf { it.title == title }
        if (removed) {
            println("Книга \"$title\" удалена из библиотеки.")
        } else {
            println("Книга \"$title\" не найдена в библиотеке.")
        }
        return removed
    }

    fun removeUserById(id: Int): Boolean {
        val removed = _users.removeIf { it.id == id }
        if (removed) {
            println("Пользователь с ID $id удален из библиотеки.")
        } else {
            println("Пользователь с ID $id не найден в библиотеке.")
        }
        return removed
    }

    fun printAllBooks() {
        println("Список книг в библиотеке $libraryName:")
        books.forEach { println("- ${it.title} by ${it.author}") }
    }

    fun printAllUsers() {
        println("Список пользователей библиотеки $libraryName:")
        users.forEach { println("- ${it.name}, ID: ${it.id}") }
    }
}

fun main() {
    val mylib = Library("mylib")
    mylib.addBook(Book("Emerald City Wizard", "Aleksandr Volkov"))
    mylib.addUser(User1("Leonid Vladimirsky", 101))

    mylib.printAllBooks()
    mylib.printAllUsers()

    mylib.removeBookByTitle("Emerald City Wizard")
    mylib.removeUserById(101)

    mylib.printAllBooks()
    mylib.printAllUsers()
}