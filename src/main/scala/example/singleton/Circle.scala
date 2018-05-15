package example.singleton

import scala.math._

// Companion objects
case class Circle (radius: Double) {
  import Circle._
  def area: Double = calculateArea(radius)
}

object Circle {
  private def calculateArea(radius: Double): Double = Pi * pow(radius, 2.0)
}

object Main {
  def main(args: Array[String]): Unit = {
    val circle1 = new Circle(5.0)

    println(circle1.area)
  }
}