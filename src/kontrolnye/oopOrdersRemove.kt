package kontrolnye

data class Order(
    val id: Int,
    val status: String,
    val type: String
)

fun removeCompletedOrders(orders: List<Order>, typeToRemove: String): List<Order> {
    val resultList = mutableListOf<Order>()

    for (order in orders) {
        val isCompleted = order.status == "completed"
        val isTargetType = order.type == typeToRemove

        if (!(isCompleted && isTargetType)) {
            resultList.add(order)
        }
    }

    return resultList.toList()
}

