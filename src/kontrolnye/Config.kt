package testworks

enum class LogLevel {
        TRACE, DEBUG, INFO, WARN, ERROR, FATAL
}

class Config(
) {
    var timeout: Int = 30
        set(value) {
            if (value !in 1..300) {
                println("Ошибка: Время ожидания должно быть в диапазоне от 1 до 300 секунд. Установлено значение по умолчанию.")
                field = 30
            } else {
                field = value
            }
        }
}