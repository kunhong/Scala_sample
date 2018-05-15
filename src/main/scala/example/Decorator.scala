package example

// 고차 함수
// 함수는 다른 함수를 파라미터로 전달받고 그것을 활용

class Decorator(left: String, right: String) {
  def layout[A](x: A)  = left + x.toString + right
}

object FunTest extends App {
  def apply(f: Int => String, v: Int)  = f(v)
  val decorator = new Decorator("[", "]")
  println(apply(decorator.layout, 7)) // [7]

  val plus = (x: Int, y: Int) => x + y
  val minus = (x: Int, y: Int) => x - y
  val multiply = (f1: (Int, Int) => Int, x: Int, y: Int, z: Int) => f1(x, y) * z

  println(plus(1, 2))
  println(minus(2, 1))
  println(multiply(plus, 2, 3, 2))
}
