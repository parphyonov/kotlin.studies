package kontrolnye.user4_13

class User(val id: Int, val name: String?, val email: String?, val phone: String?) {
    fun getDisplayName(): String {
        return name ?: "Guest"
    }

    fun getEmailOrDefault(): String {
        return email ?: "no-email@example.com"
    }

    fun getPhoneNumber(): String {
        return phone ?: "no-phone"
    }

    fun getUserInfo(): String {
        val displayName = name ?: "Unknown User"
        val emailInfo = email ?: "N/A"
        val phoneInfo = phone ?: "N/A"
        return "User Info: $displayName, Email: $emailInfo, Phone: $phoneInfo"
    }

    fun getGreeting(): String {
        return "Hello, ${name ?: "Guest"}!"
    }
}

fun calculateSum(numbers: List<Int>?): Int {
    return numbers?.sum() ?: 0
}

fun getSafeValue(value: String?): String {
    return value ?: "Default Value"
}

fun getConfigValue(config: Map<String, String>?, key: String): String {
    return config?.get(key) ?: "Not Found"
}

fun getPriority(priority: Int?): String {
    return when (priority ?: 0) {
        1 -> "High"
        2 -> "Medium"
        else -> "Low"
    }
}

fun test() {
    val user = User(1, null, "user@example.com", null)

    println(user.getDisplayName()) // Ожидается: Guest
    println(user.getEmailOrDefault()) // Ожидается: user@example.com
    println(user.getPhoneNumber()) // Ожидается: no-phone
    println(user.getUserInfo()) // Ожидается: User Info: Unknown User, Email: user@example.com, Phone: N/A
    println(user.getGreeting()) // Ожидается: Hello, Guest!

    println(calculateSum(null)) // Ожидается: 0
    println(getSafeValue(null)) // Ожидается: Default Value
    println(getConfigValue(null, "key")) // Ожидается: Not Found
    println(getPriority(null)) // Ожидается: Low
}

fun main() {
    test()
}