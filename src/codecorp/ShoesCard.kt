package codecorp

import java.io.File

class ShoesCard(
    name: String,
    brand: String,
    price: Int = 0,
    val size: Float
): ProductCard(name,
    brand,
    price,
    ProductCardTypes.SHOES
) {
    override fun serializeTo(file: File) {
        file.appendText("$name%$brand%$price%$size%$type\n")
    }

    override fun toString(): String {
        return "Name: $name, Brand: $brand, Price: $price, Size: $size"
    }
}