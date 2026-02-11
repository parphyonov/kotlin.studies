package codecorp

data class GroceriesCard(
    override val name: String,
    override val brand: String,
    override val price: Int = 0,
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
}