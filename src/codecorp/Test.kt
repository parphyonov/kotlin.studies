package codecorp

class Test (
    var number: Int
) {
    override fun equals(other: Any?): Boolean {
        if (other !is Test) return false

        return this.number == other.number
    }
}