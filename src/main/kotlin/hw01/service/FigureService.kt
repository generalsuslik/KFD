package hw01.service

interface FigureService {
    fun addCircle(property: Double)
    fun addSquare(property: Double)
    fun getArea(): Double
    fun getPerimeter(): Double
}