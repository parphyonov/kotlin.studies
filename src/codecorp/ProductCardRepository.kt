package codecorp

import java.io.File

class ProductCardRepository {
    private val file = File("product_cards.txt")

    fun checkCardsFileAndReturnCards(): MutableList<ProductCard> {
        if (!file.exists()) {
            println("Cards file doesn't exist. Add an item before proceeding")
            return mutableListOf()
        }

        val cards = loadAllCards()

        if (cards.isEmpty()) println("No cards added yet")

        return cards
    }

    fun registerNewItem(card: ProductCard) {
        if (!file.exists()) file.createNewFile()
        card.serializeTo(file)
    }

    fun removeProductCard(name: String) {
        val cards = loadAllCards()

        for (card in cards) {
            if (card.name == name) {
                cards.remove(card)
                break
            }
        }

        file.writeText("")

        for (card in cards) {
            card.serializeTo(file)
        }
    }

    private fun loadAllCards(): MutableList<ProductCard> {
        val cards = mutableListOf<ProductCard>()

        val textContent = file.readText().trim()

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
}