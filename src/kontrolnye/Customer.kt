package kontrolnye

// Примерный класс с правильной реализацией equals и hashCode с несколькими полями
class Customer(val customerId: Int, val name: String, val email: String, val address: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Customer) return false
        return customerId == other.customerId &&
                email == other.email &&
                address == other.address
    }

    override fun hashCode(): Int {
        var result = customerId
        result = 31 * result + email.hashCode()
        result = 31 * result + address.hashCode()
        return result
    }
}

// Класс User - переопределите методы equals и hashCode по userId
class User(val userId: Int, val name: String, val email: String) {
    // Переопределите equals и hashCode здесь
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        return this.userId == other.userId
    }

    override fun hashCode(): Int {
        return this.userId
    }
}

// Класс Account - переопределите методы equals и hashCode по accountId и email
class Account(val accountId: String, val userId: Int, val email: String) {
    // Переопределите equals и hashCode здесь
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Account) return false
        if (this.accountId != other.accountId) return false
        if (this.email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        var result = accountId.hashCode()
        result = 31 * result + email.hashCode()
        return result
    }
}