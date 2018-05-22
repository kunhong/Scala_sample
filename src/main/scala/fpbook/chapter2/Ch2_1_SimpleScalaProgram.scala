package fpbook.chapter2

// object : singleton
// class 와 비슷하되 명명된 인스턴스가 단 하나
// 스칼라에는 Java의 static 키워드에 해당하는 것이 없으며, Java에서 정적 멤버를 가지는 클래스를 사용할 만한 상황일 때 스칼라에서는 흔히 object를 사용한다.
object Ch2_1_SimpleScalaProgram {
  def abs(n: Int) : Int =
    if (n < 0) -n
    else n

  def factorial(n: Int): Int = {
    // 내부 함수 또는 지역 정의(local definition)
    // 함수형 프로그래밍에서 이런 함수는 지역 정수나 문자열과 다를 바 없는 값이다.
    // 루프를 함수적으로(루프 변수의 변이 없이) 작성하는 방법은 바로 재귀 함수를 이용하는 것이다.
    // 루프
    @annotation.tailrec // 자신이 작성한 재귀 함수에 대해 꼬리 호출이 실제로 제거 되었는지 확인할 필요한 있는 경우 주해를 재귀 함수에 적용
    def go(n: Int, acc: Int): Int = {
      if (n <= 0) acc
      else go(n-1, n*acc) // 스칼라의 꼬리 호출
      // 호출자가 재귀 호출의 결과를 그대로 돌려주는 것 외에는 아무 일도 하지 않을 때, 그런 호출을 꼬리 위치(tail position)에서의 호출
      // 줄여서 꼬리 호출이라고 말한다.
      // 한 함수가 수행하는 모든 재귀 호출이 꼬리 호출이면, 스칼라는 해당 재귀를 매 반복마다 호출 스택을 소비하지 않는 반복 루프 형태로 컴파일한다.
    }

    go(n, 1)
  }

  // 스칼라가 메서드의 반환 형식을 추론할 수 있는 경우가 많으며, 그럴 때에는 반환 형식을 생략할 수 있다.
  private def formatAbs(x: Int) = {
    val msg = "The absolute value of %d is %d"
    msg.format(x, abs(x))
  }

  private def formatFactorial(n: Int) = {
    val msg = "The factorial value of %d is %d"
    msg.format(n, factorial(n))
  }

  // formatAbs 와 formatFactorial는 거의 동일
  // f 가 정수 인수 하나를 받고 정수 하나를 돌려준다는 뜻
  def formatResult(name: String, n: Int, f: Int => Int) = {
    val msg = "The %s of %d is %d."
    msg.format(name, n, f(n))
  }

  def fib(n: Int): Int = {
    @annotation.tailrec
    def loop(n: Int, prev: Int, cur: Int): Int = {
      if (n == 0) prev
      else loop(n-1, cur, prev + cur)
    }

    loop(n, 0, 1)
  }

  // 일반적 함수(generic function)라고 부르는 다형적 함수의 한 예
  // 이 함수는 형식에 대한 추상(abstracting over the type)을 배열과 배열 안의 한 요소를 검색하는 함수에 적용한 결과이다.
  def findFirst[A](as: Array[A], p: A => Boolean): Int = {
    @annotation.tailrec
    def loop(n: Int): Int = {
      if (n >= as.length) -1
      else if (p(as(n))) n
      else loop(n + 1)
    }

    loop(0)
  }

  // main : 콘솔에 출력하는 외부 계층(shell)
  // 부수 효과가 발생함을 강조하기 위해, 이런 메서드를 절차(procedure) 또는 불순 함수(impure function)라고 부르기도 한다.
  def main(args: Array[String]): Unit = {
    println(fib(2))

    println(formatAbs(-42)) // The absolute value of -42 is 42
    println(formatFactorial(4)) // The factorial value of 4 is 24

    println(formatResult("absolute value", -42, abs)) // The absolute value of -42 is 42.
    println(formatResult("factorial", 7, factorial)) // The factorial of 7 is 5040.


    // 익명 함수(anonymous) 또는 함수 리터럴(function literal)을 지정해서 호출 : (x : Int) => x == 9)
    // 함수 리터럴을 정의되는 것은 실제로 apply 라는 메서드를 가진 하나의 객체임
    // 예를들어, 아래의 함수 리터럴은 다음과 같다.
//    val eqaul9 = new Function1[Int, Boolean] {
//      override def apply(v1: Int): Boolean = v1 == 9
//    }
    // 인수 개수에 따라 Function1, Function2, ... 같은 여러 다른 trait 을 제공한다.
    // 함수는 보통 스칼라 객체이며, 이를 흔히 함수는 일급(first-class) 값이다 라고 말한다.
    println(findFirst(Array(7, 9, 13), (x : Int) => x == 9)) // 1

    // 부분 적용(partial application)
    // 인수가 2개인 함수를 받아서 그것을 부분적으로 적용하는 고차 함수
    def partial1[A,B,C](a: A, f: (A, B) => C) : B => C = (b: B) => f(a, b)
    // def partial1[A,B,C](a: A, f: (A, B) => C) : B => C = b => f(a, b)
    // b에 대한 형식을 필요가 없다. 이미 반환 형식을 B => C 로 명시 했으므로, 구현은 그냥 b => f(a,b)라고 펴기해도 스칼라는 문맥에서 b의 형식을 추론 할 수 있다.

    def curry[A,B,C](f: (A, B) => C): A => (B => C) = a => b => f(a,b)

    // 함수 합성(functional composition)
    // 한 함수의 출력을 다른 함수의 입력으로 공급한다.
    // 이미 Function1 의 compose 메서드를 제공
    // 만약 f 와 g를 합성하려면 그냥 f compose g 라고 하면 된다.
    // 또한 andThen 이라는 메서드도 제공한다 // f andThen g == g compose f
    def compose[A,B,C](f: B => C, g: A => B): A => C = a => f(g(a))

    val f = (x: Double) => math.Pi / 2 - x
    val cos = f andThen math.sin

    val f1 = (x: Int) => x * 2
    val f2 = (x: Int) => x + 1

    val f1_compose_f2 = f1 compose f2 // f2 가 실행되고 f1 결국 f1(f2)
    println(f1_compose_f2(10)) // 22

    val f1_andThen_f2 = f1 andThen f2 // f1 가 실행되고 f2 결국 f2(f1)
    println(f1_andThen_f2(10)) // 21
  }

}
