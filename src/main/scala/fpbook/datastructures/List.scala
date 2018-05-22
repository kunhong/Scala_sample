package fpbook.datastructures

sealed trait List[+A]// 형식 A에 대해 매개변수화된 List 자료 형식
// 일반적으로 자료 형식을 도입할 때에는 trait 키워드를 사용한다.
// trait 키워드로 정의하는 특질은 하나의 추상 인터페이스로, 필요하다면 일부 메서드의 구현을 담을 수 있다.
// 위 특질에는 메서드가 하나도 없다. trait 앞에 sealed를 붙이는 것은 이 특질의 모든 구현이 반드시 이 파일 안에 선언되어 있어야 함을 뜻한다.
// 형식 매개 변수에 있는 +는 공변(covariant)를 뜻한다.
// 결국 하나의 동일한 정의로 List[Int], List[Double], List[String]등을 ㅅ용할 수 있게 된다.
// 여기서 매개변수 A 앞에 붙은 +는 A가 List의 공변 매개 변수임을 뜻하는 가변 지정자(variance annotation)이다.
// 만약 A 앞에 +가 없으면 그 형식 매개 변수에 대해 List는 불변(invariant)이다.

case object Nil extends List[Nothing] // 빈 목록을 나타내는 List 자료 생성자
case class Cons[+A](head: A, tail: List[A]) extends List[A] // 비지 않은 목록을 나타내는 또 다른 자료 생성자. tail은 또 다른 List[A]로 Nil일 수도 있고 다른 Cons일 수도 있다.
// List("a", "b") == Cons("a", Cons("b", Nil))

object List {

  // 아래 sum 과 product 함수들을 List 의 동반 객체(companion object)라고 부르기도 한다.
  def sum(ints : List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs) // 일반적으로 순차열(sequence)을 나타내는 변수의 이름으로는 xs, ys, as, bs 등을 사용한다.
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  // 함수가 A 형식의 인수를 0개 이상 받을 수 있음을 뜻한다.
  // 자료 형식을 만들 때에는, 자료 형식의 인스턴스를 편리하게 생성하기 위해 가변 인수 apply 메서드를 자료 형식의 동반 객체에 집어넣는 관례가 흔히 쓰인다.
  def apply[A](as: A*): List[A] = { // 가변 인수 함수 구문
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
  }

  def main(args: Array[String]): Unit = {
    println(List(1,2,3) match {case _ => 42}) // 42
    println(List(1,2,3) match {case Cons(_, tail) => tail}) // Cons(2,Cons(3,Nil)) == List(2,3)
    println(List(1,2,3)) // Cons(1,Cons(2,Cons(3,Nil)))
  }

}
