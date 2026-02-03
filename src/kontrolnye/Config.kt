package kontrolnye

const val DEFAULT_TIMEOUT = 30
const val TIMEOUT_LOWER_BOUNDARY = 1
const val TIMEOUT_UPPER_BOUNDARY = 300

const val DEFAULT_MAX_RETRIES = 3

enum class LogLevel {
    TRACE, DEBUG, INFO, WARN, ERROR, FATAL
}

class Config {
    var timeout: Int = DEFAULT_TIMEOUT
        set(value) {
            if (value !in TIMEOUT_LOWER_BOUNDARY..TIMEOUT_UPPER_BOUNDARY) {
                val formattedMsg = "Время ожидания должно быть в диапазоне от $TIMEOUT_LOWER_BOUNDARY " +
                    "до $TIMEOUT_UPPER_BOUNDARY секунд"
                defaultSettingDisclaimer(formattedMsg)
                field = DEFAULT_TIMEOUT
            } else {
                field = value
            }
        }

    var maxRetries: Int = DEFAULT_MAX_RETRIES
        set(value) {
            if (value < 0) {
                defaultSettingDisclaimer("Максимальное количество повторных попыток не может быть отрицательным")
                field = DEFAULT_MAX_RETRIES
            } else {
                field = value
            }
        }

    var loggingLevel: LogLevel = LogLevel.INFO
        set(value) {
            when (value) {
                LogLevel.TRACE, LogLevel.FATAL -> {
                    println("Ошибка: Уровень $value недоступен.")
                    field = field
                }

                else -> field = value
            }
        }

    val isDebugMode: Boolean
        get() = loggingLevel == LogLevel.DEBUG

    val isProductionMode: Boolean
        get() = loggingLevel == LogLevel.ERROR

    fun printConfig() {
        println("Время ожидания: $timeout секунд")
        println("Максимальное количество повторных попыток: $maxRetries")
        println("Уровень логирования: ${loggingLevel.name}")
        println("Режим отладки: $isDebugMode")
        println("Режим продакшн: $isProductionMode")
    }

    fun defaultSettingDisclaimer(message: String) {
        print("Ошибка: $message. Установлено значение по умолчанию.")
    }
}
