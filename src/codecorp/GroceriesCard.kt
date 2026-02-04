package codecorp

import java.io.File

class GroceriesCard(
    name: String,
    brand: String,
    price: Int = 0,
    val calories: Int
): ProductCard(
    name,
    brand,
    price,
    ProductCardTypes.GROCERIES
) {
    override fun serialize(): String {
        return "$name%$brand%$price%$calories%$type\n"
    }

    override fun toString(): String {
        return "Name: $name, Brand: $brand, Price: $price, Calories: $calories"
    }
}