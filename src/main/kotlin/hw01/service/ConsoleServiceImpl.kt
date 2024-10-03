package hw01.service

import hw01.exception.BadPropertyException
import hw01.exception.WrongOperationTypeException

class ConsoleServiceImpl : ConsoleService {

    private val service = FigureServiceImpl()

    private enum class Operation {
        INSERT,
        GET_AREA,
        GET_PERIMETER,
        EXIT
    }

    private fun getOperation(input: String): Operation {
        return when(input.toIntOrNull()) {
            1 -> Operation.INSERT
            2 -> Operation.GET_AREA
            3 -> Operation.GET_PERIMETER
            4 -> Operation.EXIT
            else -> throw WrongOperationTypeException("Wrong operation type entered: $input")
        }
    }

    private fun addFigure() {
        println("Enter figure name: circle/square")
        val name: String = readln()
        if (name != "circle" && name != "square") {
            return
        }

        println("Enter property value")
        val property: Double = readlnOrNull()?.toDoubleOrNull() ?: throw BadPropertyException(
            "Property must be a number"
        )

        if (property <= 0.0) {
            throw BadPropertyException("Property must be > 0")
        }

        when (name) {
            "circle" -> service.addCircle(property)
            "square" -> service.addSquare(property)
        }
    }

    override fun work() {
        while (true) {
            println("Enter operation type to execute:")
            println("1) Add figure")
            println("2) Get area of all figures")
            println("3) Get perimeter of all figures")
            println("4) Exit")

            try {
                val operation = getOperation(readln())

                when (operation) {
                    Operation.INSERT -> addFigure()
                    Operation.GET_AREA -> println("Figures area: ${service.getArea()}")
                    Operation.GET_PERIMETER -> println("Figures perimeter: ${service.getPerimeter()}")
                    Operation.EXIT -> break
                }
            } catch (e: BadPropertyException) {
                println(e.message)
            }
        }
    }
}