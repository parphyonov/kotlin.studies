package profile

const val MIN_PWD_LEN = 8

class PasswordHasher {
    fun hashPassword(password: String): String = password.reversed()
}

class UserRegistrationService {
    private val registeredUsers = mutableListOf<String>()
    private val hashingMachine = PasswordHasher()

    fun validateUserData(username: String, password: String): Boolean {
        return username.isNotBlank() && password.length >= MIN_PWD_LEN
    }

    fun saveUser(username: String, passwordHash: String) {
        registeredUsers.add("$username:$passwordHash")
    }

    fun registerUser(username: String, password: String): Boolean {
        if (!validateUserData(username, password)) {
            println("Invalid user data")
            return false
        }

        val passwordHash = hashingMachine.hashPassword(password)
        saveUser(username, passwordHash)
        println("User registered successfully")
        return true
    }
}