package codecorp

class AppliancesCard(
    name: String,
    brand: String,
    price: Int = 0,
    val wattage: Int
): ProductCard(
    name,
    brand,
    price,
    ProductCardTypes.APPLIANCES
) {
    override fun serialize(): String {
        return "$name%$brand%$price%$wattage%$type\n"
    }

    override fun toString(): String {
        return "Name: $name, Brand: $brand, Price: $price, Wattage: $wattage"
    }
}