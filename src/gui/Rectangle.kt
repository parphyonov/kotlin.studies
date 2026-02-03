package gui

class Rectangle(
    val width: Int = 0,
    val height: Int = 0
) {
    constructor(side: Int): this(side, side)

    fun draw() {
        // Division line between figures
        print("\n")

        // Upper line
        repeat ( width) {
            print("*")
        }
        print("\n")

        // Middle parts
        repeat(height - 2) {
            print("*")

            repeat(width - 2) {
                print(" ")
            }

            print("*\n")
        }

        // Lower line
        repeat (width) {
            print("*")
        }
    }
}