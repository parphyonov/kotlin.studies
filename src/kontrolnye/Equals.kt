package kontrolnye

// Класс с переопределенным методом equals по id
class Person(val id: Int, val name: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Person) return false
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return id
    }
}

// Класс с переопределенным методом equals по uniqueCode
class Product(val uniqueCode: String, val name: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Product) return false
        return this.uniqueCode == other.uniqueCode
    }

    override fun hashCode(): Int {
        return uniqueCode.hashCode()
    }
}

// Класс без переопределенного метода equals, сравнение происходит по ссылке
class Order(val orderId: Int, val description: String)

// Класс с тестами для проверки методов equals, ==, и ===
class TestCases {
    fun runTests() {
        val person1 = Person(1, "Alice")
        val person2 = Person(1, "Alicia") // Равен по id
        val person3 = Person(2, "Bob")
        val person4 = person1 // Ссылка на тот же объект

        // Тесты для Person
        println("=== Тесты для Person ===")

        // Тест 1: Сравнение по значению
        if (person1 == person2) println("Test 1 passed") else throw Exception("Test 1 failed")

        // Тест 2: Сравнение по ссылке
        if (person1 === person2) throw Exception("Test 2 failed") else println("Test 2 passed")

        // Тест 3: Сравнение по значению
        if (person1 != person3) println("Test 3 passed") else throw Exception("Test 3 failed")

        // Тест 4: Сравнение по ссылке
        if (person1 === person4) println("Test 4 passed") else throw Exception("Test 4 failed")

        // Тест 5: Сравнение по ссылке
        if (person1 == person2) println("Test 5 passed") else throw Exception("Test 5 failed")

        val person5 = Person(3, "Charlie")
        val person6 = Person(3, "Charles") // С тем же id, но другим именем

        // Тест 6: Сравнение по значению
        if (person5 == person6) println("Test 6 passed") else throw Exception("Test 6 failed")

        // Тесты для Product
        val product1 = Product("ABC123", "Laptop")
        val product2 = Product("ABC123", "Notebook") // Равен по uniqueCode
        val product3 = Product("XYZ456", "Tablet")
        val product4 = product1 // Ссылка на тот же объект

        println("=== Тесты для Product ===")

        // Тест 7: Сравнение по значению
        if (product1 == product2) println("Test 7 passed") else throw Exception("Test 7 failed")

        // Тест 8: Сравнение по ссылке
        if (product1 === product2) throw Exception("Test 8 failed") else println("Test 8 passed")

        // Тест 9: Сравнение по значению
        if (product1 != product3) println("Test 9 passed") else throw Exception("Test 9 failed")

        // Тест 10: Сравнение по ссылке
        if (product1 === product4) println("Test 10 passed") else throw Exception("Test 10 failed")

        // Тест 11: Сравнение по ссылке
        if (product1 !== product2) println("Test 11 passed") else throw Exception("Test 11 failed")

        val product5 = Product("DEF789", "Phone")
        val product6 = Product("DEF789", "Smartphone") // С тем же uniqueCode, но другим именем

        // Тест 12: Сравнение по значению
        if (product5 == product6) println("Test 12 passed") else throw Exception("Test 12 failed")

        // Тесты для Order
        val order1 = Order(100, "First order")
        val order2 = Order(100, "Duplicate first order") // Должен быть уникальным объектом
        val order3 = Order(101, "Second order")
        val order4 = order1 // Ссылка на тот же объект

        println("=== Тесты для Order ===")

        // Тест 13: Сравнение по значению
        if (order1 != order2) println("Test 13 passed") else throw Exception("Test 13 failed")

        // Тест 14: Сравнение по ссылке
        if (order1 !== order2) println("Test 14 passed") else throw Exception("Test 14 failed")

        // Тест 15: Сравнение по значению
        if (order1 != order3) println("Test 15 passed") else throw Exception("Test 15 failed")

        // Тест 16: Сравнение по ссылке
        if (order1 !== order3) println("Test 16 passed") else throw Exception("Test 16 failed")

        // Тест 17: Сравнение по ссылке
        if (order1 === order4) println("Test 17 passed") else throw Exception("Test 17 failed")

        // Дополнительные тесты для проверки
        val person7 = Person(4, "Dave")
        val person8 = Person(4, "David")

        // Тест 18: Сравнение по значению
        if (person7 == person8) println("Test 18 passed") else throw Exception("Test 18 failed")

        val product7 = Product("XYZ999", "Tablet")
        val product8 = Product("XYZ999", "Tab")

        // Тест 19: Сравнение по значению
        if (product7 == product8) println("Test 19 passed") else throw Exception("Test 19 failed")

        val order5 = Order(102, "Third order")
        val order6 = Order(103, "Fourth order")

        // Тест 20: Сравнение по значению
        if (order5 != order6) println("Test 20 passed") else throw Exception("Test 20 failed")

        // Тест 21: Сравнение по ссылке
        if (order5 !== order6) println("Test 21 passed") else throw Exception("Test 21 failed")

        // Тест 22: Сравнение по значению
        if (order5 != order3) println("Test 22 passed") else throw Exception("Test 22 failed")

        // Тест 23: Сравнение по ссылке
        if (product7 !== product8) println("Test 23 passed") else throw Exception("Test 23 failed")

        // Тест 24: Сравнение по ссылке
        if (person7 !== person8) println("Test 24 passed") else throw Exception("Test 24 failed")
    }
}

fun main() {
    val myTests = TestCases()
    myTests.runTests()
}