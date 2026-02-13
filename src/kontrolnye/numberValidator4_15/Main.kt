package kontrolnye.numberValidator4_15

/**
 * Класс NumberValidator выполняет проверку чисел.
 */
object NumberValidator {

    /**
     * Завершает выполнение программы или выбрасывает исключение с заданным сообщением.
     * Возвращает тип Nothing.
     */
    fun terminate(message: String): Nothing {
        throw IllegalArgumentException(message)
    }

    /**
     * Проверяет число на корректность.
     * Если число некорректно, вызывает terminate.
     * @return то же число, если данные корректны.
     */
    fun validate(number: Int): Int = when (number) {
        in Int.MIN_VALUE until 0 -> terminate("Ошибка: Число отрицательное.")
        0 -> terminate("Ошибка: Число равно нулю.")
        else -> number
    }
}

/**
 * Функция обработки числа.
 * Проверяет число с помощью NumberValidator.validate и выводит результат.
 */
fun processNumber(number: Int) {
    try {
        NumberValidator.validate(number)
        println("Число: $number")
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }
}

fun main() {
    processNumber(5)
    processNumber(0)
    processNumber(-5)
}