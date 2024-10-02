package hw01

class Square(private val side: Double) : Figure("Square", side) {
    init {
        println("Square(property=${this.side})")
    }

    override fun getArea(): Double {
        return this.side * this.side
    }

    override fun getPerimeter(): Double {
        return this.side * 4
    }
}