package codecorp

data class AppliancesCard(
    override val name: String,
    override val brand: String,
    override val price: Int = 0,
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
}