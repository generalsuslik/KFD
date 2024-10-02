package hw01

class FigureServiceImpl : FigureService {
    private val figList: MutableList<Figure> = mutableListOf()
    override fun addCircle(property: Double) {
        val circle = Circle(property)
        figList.add(circle)
    }

    override fun addSquare(property: Double) {
        val square = Square(property)
        figList.add(square)
    }

    override fun getArea(): Double {
        var area: Double = 0.0
        figList.forEach {
            area = it.getArea()
        }

        return area
    }

    override fun getPerimeter(): Double {
        var perimeter: Double = 0.0
        figList.forEach {
            perimeter += it.getPerimeter()
        }

        return perimeter
    }
}