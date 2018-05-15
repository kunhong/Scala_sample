package example

object FunctionTest {
  def main(args: Array[String]): Unit = {
    // x 는 Int를 받아서 Int를 반환하는 함수
    // functionExpression 는 x함수를 인수로 받는 함수
    // => 기호를 사용하는 것을 함수 표현식이라고 함
    def functionExpression(x : (Int => Int)) = x

    // Int 를 받아서 10을 더해 반환하는 함수
    val functionAsValue = (y: Int) => y + 10
    /*
    * val functionAsValue: Int => Int = new Function[Int, Int] {
    *   def apply(y: Int): Int = y + 10
    * }
    * */

    // val g가 f라는 함수를 나타내는 객체를 가지기 위해서는 특별한 방법이 필요 합니다.

    // 1. _ 를 이용해 f를 호출하는 곳에서 f를 부분 적용 함수 형태로 바꿔주어 값으로 만드는 방법. 부분 적용 함수는 항상 함수 객체
    val g = f _

    // 2. 반환 자료형을 명시적으로 사용
    val g1 : (Int => Int) = f

    // f1을 선언하면서 =>를 사용
    def f1 = (i: Int) => i
    val g2 = f1

    println(f(1))  // 1

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    val result = calc(x => x * x, 2, 5)
    println(result) // 54

    printlnStrings("string1", "string2", "string3")
    // string1
    // string2
    // string3

    println(default()) // 9

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // implicit 함수
    def doubleToInt(d: Double) = d.toInt
    val x: Int = doubleToInt(18.0) // x 에는 18이 들어감
    // val x2: Int = 18.0 // 컴파일 에러 발생

    // doubleToInt는 어차피 Double을 받아서 Int를 보내주는 함수이기 때문에 이러한 적당한 함수가 존재한다면 자동적으로 해당 함수를 컴파일할 때 연결해주면 어떨까?
    implicit def doubleToInt2(d: Double) = d.toInt
    val x2: Int = 18.0

  }

  def default(a: Int = 4, b: Int = 5):Int = a + b

  // String* : 여러개의 String을 받을 수 있다.
  def printlnStrings(args: String*) = {
    for (arg <- args) {
      println(arg)
    }
  }

  def calc(f: Int=> Int, start: Int, end: Int) = {
    def loop(index: Int, sum: Int): Int = {
      if (index > end) sum
      else loop(index + 1, f(index) + sum)
    }

    loop(start, 0)
  }

  def f(i: Int) = i
}
