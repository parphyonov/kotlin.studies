package codecorp

data class ShoesCard(
    override val name: String,
    override val brand: String,
    override val price: Int = 0,
    val size: Float
): ProductCard(
    name,
    brand,
    price,
    ProductCardTypes.SHOES
) {
    override fun serialize(): String {
        return "$name%$brand%$price%$size%$type\n"
    }
}