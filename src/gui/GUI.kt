package gui

import gui.Rectangle

fun main() {
    val myRect = Rectangle(10, 5)
    myRect.draw()

    val mySquare = Rectangle(3)
    mySquare.draw()

    val myFigure = Rectangle()
    myFigure.draw()
}