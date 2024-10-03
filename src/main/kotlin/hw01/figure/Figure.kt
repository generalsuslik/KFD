package hw01.figure

internal open class Figure(val name: String, val property: Double) {

    open fun getArea(): Double {
        return 0.0
    }

    open fun getPerimeter(): Double {
        return 0.0
    }
}