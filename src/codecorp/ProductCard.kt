package codecorp

abstract class ProductCard(
    open val name: String,
    open val brand: String,
    open val price: Int,
    val type: ProductCardTypes
) {
    abstract fun serialize(): String
}