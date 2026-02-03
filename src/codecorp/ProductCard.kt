package codecorp

import java.io.File

abstract class ProductCard(
    val name: String,
    val brand: String,
    val price: Int,
    val type: ProductCardTypes
) {
    abstract fun serializeTo(file: File)
}