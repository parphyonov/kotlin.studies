package kontrolnye

data class Order1(
    val id: Int,
    val status: String,
    val type: String
)

fun removeCompletedOrders(orders: List<Order1>, typeToRemove: String): List<Order1> {
    val resultList = mutableListOf<Order1>()

    for (order in orders) {
        val isCompleted = order.status == "completed"
        val isTargetType = order.type == typeToRemove

        if (!(isCompleted && isTargetType)) {
            resultList.add(order)
        }
    }

    return resultList.toList()
}

