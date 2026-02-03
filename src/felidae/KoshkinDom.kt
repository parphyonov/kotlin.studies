package felidae

fun main() {
    val cat = Cat("Felix")
    val lion = Lion(25)

    // cat props
    println(cat.petName)
    println(cat.legsCount)
    cat.playWithMouse()

    // lion props
    println(lion.prideSize)
    println(lion.legsCount)
}