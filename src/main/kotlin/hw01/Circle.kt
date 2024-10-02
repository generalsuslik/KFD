package hw01

import kotlin.math.PI


class Circle(private val radius: Double) : Figure("Circle", radius) {
    init {
        println("Circle(property=${this.radius})")
    }

    override fun getArea(): Double {
        return this.radius * this.radius * PI
    }

    override fun getPerimeter(): Double {
        return 2 * PI * this.radius
    }
}