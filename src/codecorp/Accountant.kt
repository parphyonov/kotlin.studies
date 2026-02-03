package codecorp

import java.io.File
import kotlin.text.toInt

class Accountant(
    id: Int,
    name: String,
    age: Int = 0,
    salary: Int
) : Employee(
    id = id,
    name = name,
    age = age,
    position = Positions.ACCOUNTANT,
    salary = salary
), Cleaner, Supplier {
    private val cardsFile = File("product_cards.txt")
    private val employeesFile = File("employees.csv")

    override fun work() {
        val accountantOperations = AccountantOperations.entries

        var shiftOver = false

        while (!shiftOver) {
            print("Enter the operation code. ")
            printMenuFromEnum(accountantOperations, true)

            val accountantOperationIndex = readln().toInt()

            if (accountantOperationIndex !in 0..<accountantOperations.size) continue

            val accountantOperationToPerform = accountantOperations[accountantOperationIndex]

            when (accountantOperationToPerform) {
                AccountantOperations.EXIT -> shiftOver = true
                AccountantOperations.REGISTER_NEW_ITEM -> registerNewItem()
                AccountantOperations.SHOW_ALL_ITEMS -> showAllItems()
                AccountantOperations.REMOVE_PRODUCT_CARD -> removeProductCard()
                AccountantOperations.REGISTER_NEW_EMPLOYEE -> registerNewEmployee()
                AccountantOperations.SHOW_ALL_EMPLOYEES -> showAllEmployees()
                AccountantOperations.FIRE_EMPLOYEE -> fireEmployee()
                AccountantOperations.CHANGE_SALARY -> changeSalary()
            }
        }
    }

    private fun changeSalary() {
        val employees = loadAllEmployees()

        print("Enter employee's ID for salary update: ")
        val id = readln().toInt()

        print("Enter new salary: ")
        val newSalary = readln().toInt()

        for (employee in employees)
            if (id == employee.id) {
                employee.setSalary(newSalary)
                break
            }

        rewrite(employees)
    }

    private fun fireEmployee() {
        val employees = loadAllEmployees()

        print("Enter employee's ID (for firing): ")
        val id = readln().toInt()

        for (employee in employees) {
            if (employee.id == id) {
                employees.remove(employee)

                rewrite(employees)

                break
            }
        }
    }

    private fun rewrite(employees: MutableList<Employee>) {
        employeesFile.writeText("")

        for (employee in employees) {
            employee.serializeTo(employeesFile)
        }
    }

    fun loadAllEmployees(): MutableList<Employee> {
        val employees = mutableListOf<Employee>()

        val textContent = employeesFile.readText().trim()

        if (textContent.isEmpty()) return employees

        val rawEmployees = textContent.split("\n")

        for (rawEmployee in rawEmployees) {
            val splitEmployee = rawEmployee.split(",")

            if (splitEmployee.size != 5) continue

            val (id, name, age, salary, position) = splitEmployee

            val employee = when (Positions.valueOf(position)) {
                Positions.DIRECTOR -> Director(id.toInt(), name, age.toInt(), salary.toInt())
                Positions.ASSISTANT -> Assistant(id.toInt(), name, age.toInt(), salary.toInt())
                Positions.CONSULTANT -> Consultant(id.toInt(), name, age.toInt(), salary.toInt())
                Positions.ACCOUNTANT -> Accountant(id.toInt(), name, age.toInt(), salary.toInt())
            }

            employees.add(employee)
        }

        return employees
    }

    private fun showAllEmployees() {
        if (!employeesFile.exists()) {
            println("Employees file doesn't exist. Add an item before proceeding")
            return
        }

        val employees = loadAllEmployees()

        if (employees.isEmpty()) println("No employees created yet")

        for (employee in employees) {
            println(employee)
        }
    }

    private fun isUniqueIDInAList(employees: MutableList<Employee>, id: Int): Boolean {
        return employees.none { it.id == id }
    }

    private fun registerNewEmployee() {
        if (!employeesFile.exists()) employeesFile.createNewFile()

        val positions = Positions.entries
        val employees = loadAllEmployees()

        var newEmployeeIndex = -1
        var id: Int

        while (newEmployeeIndex !in 0..<positions.size) {
            print("Select an employee type to add. ")
            printMenuFromEnum(positions)
            newEmployeeIndex = readln().toInt()
        }

        while (true) {
            print("Enter new employee's ID: ")
            id = readln().toInt()

            if (id < 0) {
                println("Please, pick up a positive ID")
                continue
            } else if (isUniqueIDInAList(employees, id)) break
            else println("This id is not unique. Try a different one!")
        }

        print("Enter new employee's name: ")
        val name = readln()
        print("Enter new employee's age: ")
        val age = readln().toInt()
        print("Enter new employee's salary: ")
        val salary = readln().toInt()

        val positionToAdd = positions[newEmployeeIndex]

        val employee = when (positionToAdd) {
            Positions.DIRECTOR -> Director(id, name, age, salary)
            Positions.ASSISTANT -> Assistant(id, name, age, salary)
            Positions.CONSULTANT -> Consultant(id, name, age, salary)
            Positions.ACCOUNTANT -> Accountant(id, name, age, salary)
        }

        employee.serializeTo(employeesFile)
    }

    private fun removeProductCard() {
        val cards = loadAllCards()

        print("Enter the name of the card to delete: ")
        val cardToRemove = readln()

        for (card in cards) {
            if (card.name == cardToRemove) {
                cards.remove(card)
                break
            }
        }

        cardsFile.writeText("")

        for (card in cards) {
            card.serializeTo(cardsFile)
        }
    }

    private fun loadAllCards(): MutableList<ProductCard> {
        val cards = mutableListOf<ProductCard>()

        val textContent = cardsFile.readText().trim()

        if (textContent.isEmpty()) return cards

        val rawItems = textContent.split("\n")

        for (item in rawItems) {
            val splitItem = item.split("%")
            if (splitItem.size < 5) continue

            val name = splitItem[0]
            val brand = splitItem[1]
            val price = splitItem[2].toInt()
            val type = splitItem.last()

            val card = when (ProductCardTypes.valueOf(type)) {
                ProductCardTypes.APPLIANCES -> {
                    AppliancesCard(name, brand, price, splitItem[3].toInt())
                }

                ProductCardTypes.GROCERIES -> {
                    GroceriesCard(name, brand, price, splitItem[3].toInt())
                }

                ProductCardTypes.SHOES -> {
                    ShoesCard(name, brand, price, splitItem[3].toFloat())
                }
            }
            cards.add(card)
        }

        return cards
    }

    private fun showAllItems() {
        if (!cardsFile.exists()) {
            println("Cards file doesn't exist. Add an item before proceeding")
            return
        }

        val cards = loadAllCards()

        if (cards.isEmpty()) println("No cards added yet")

        for (card in cards) {
            println(card)
        }
    }

    private fun registerNewItem() {
        if (!cardsFile.exists()) cardsFile.createNewFile()

        val productCardTypes = ProductCardTypes.entries

        var productCardIndex = -1

        while (productCardIndex !in 0..<productCardTypes.size) {
            print("Enter the product type. ")
            printMenuFromEnum(productCardTypes)

            productCardIndex = readln().toInt()
        }

        print("Enter product's name: ")
        val name = readln()

        print("Enter product's brand: ")
        val brand = readln()

        print("Enter product's price: ")
        val price = readln().toInt()

        val productCardToRegister = productCardTypes[productCardIndex]

        val card = when (productCardToRegister) {
            ProductCardTypes.APPLIANCES -> {
                print("Enter appliance's wattage: ")
                val wattage = readln().toInt()
                AppliancesCard(name, brand, price, wattage)
            }

            ProductCardTypes.GROCERIES -> {
                print("Enter grocery's caloric: ")
                val calories = readln().toInt()
                GroceriesCard(name, brand, price, calories)
            }

            ProductCardTypes.SHOES -> {
                print("Enter appliance's size: ")
                val size = readln().toFloat()
                ShoesCard(name, brand, price, size)
            }
        }

        card.serializeTo(cardsFile)
    }

    fun <E : Enum<E>> printMenuFromEnum(entries: List<E>, onSeparateLines: Boolean = false) {
        if (onSeparateLines) println()

        for ((index, value) in entries.withIndex()) {
            val formattedName = value.name
                .split("_").joinToString(" ")
                .lowercase().replaceFirstChar { it.uppercase() }

            if (onSeparateLines) {
                println("$index - $formattedName")
            } else {
                print("$index - $formattedName")
                if (index < entries.lastIndex) {
                    print(", ")
                } else {
                    print(": ")
                }
            }
        }
    }

    override fun clean() {
        if (id == 0) println("JARVIS: I don't clean")
        else println("[ID#$id]:>> ${name.lowercase()}@${position.name.lowercase()}.code: I clean")
    }

    override fun buyThings() {
        if (id == 0) println("JARVIS: I don't buy")
        else println("[ID#$id]:>> ${name.lowercase()}@${position.name.lowercase()}.code: I buy things")
    }
}