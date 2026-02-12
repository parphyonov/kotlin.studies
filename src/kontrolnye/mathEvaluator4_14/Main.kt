package kontrolnye.mathEvaluator4_14

/**
 * Класс MathEvaluator выполняет вычисления переданных математических выражений.
 */
object MathEvaluator {

    /**
     * Выполняет вычисление математического выражения, представленного в виде строки.
     * Поддерживает только операции сложения, вычитания, умножения и деления двух чисел.
     * @param expression строка вида "a + b" или "a / b"
     * @return результат вычисления
     * @throws ArithmeticException если происходит деление на ноль
     * @throws Exception для любых других ошибок
     */
    fun evaluate(expression: String): Double {
        val parts = expression.split(" ")
        if (parts.size != 3) throw Exception("Некорректный формат")

        val a = parts[0].toDoubleOrNull() ?: throw Exception("Некорректное число")
        val b = parts[2].toDoubleOrNull() ?: throw Exception("Некорректное число")
        val operator = parts[1]

        return when (operator) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> {
                if (b == 0.0) throw ArithmeticException("Деление на ноль")
                a / b
            }
            else -> throw Exception("Некорректный оператор")
        }
    }
}

// Основная функция программы
fun processExpressions() {
    val expressions = listOf("10 + 5", "20 / 0", "abc * 5", "15 ^ 2", "30 / 5")

    for (expression in expressions) {
        try {
            val result = MathEvaluator.evaluate(expression)
            println("Результат выражения '$expression': $result")
        } catch (e: ArithmeticException) {
            println("Ошибка: ${e.message}.")
        } catch (e: Exception) {
            println("Ошибка: Некорректное выражение.")
        }
    }
}

fun main() {
    processExpressions()
}