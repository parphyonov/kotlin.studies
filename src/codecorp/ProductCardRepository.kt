package codecorp

import java.io.File

object ProductCardRepository {
    private val file = File("product_cards.txt")

    private val _cards = loadAllCards()
    val cards
        get() = _cards.toList()

    fun saveChanges() {
        if (!file.exists()) {
            file.createNewFile()
        }

        if (_cards.isEmpty()) {
            println("No cards added yet")
        } else {
            val content = StringBuilder()

            for (card in _cards) {
                content.append(card.serialize())
            }

            file.writeText(content.toString())
        }
    }

    fun registerNewItem(card: ProductCard) {
        _cards.add(card)
    }

    fun removeProductCard(name: String) {
        for (card in cards) {
            if (card.name == name) {
                _cards.remove(card)
                break
            }
        }
    }

    private fun loadAllCards(): MutableList<ProductCard> {
        val list = mutableListOf<ProductCard>()

        val textContent = file.readText().trim()

        if (textContent.isEmpty()) return list

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
            list.add(card)
        }

        return list
    }
}