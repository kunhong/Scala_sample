package fpbook.datastructures

sealed trait List[+A]// 형식 A에 대해 매개변수화된 List 자료 형식
// 일반적으로 자료 형식을 도입할 때에는 trait 키워드를 사용한다.
// trait 키워드로 정의하는 특질은 하나의 추상 인터페이스로, 필요하다면 일부 메서드의 구현을 담을 수 있다.
// 위 특질에는 메서드가 하나도 없다. trait 앞에 sealed를 붙이는 것은 이 특질의 모든 구현이 반드시 이 파일 안에 선언되어 있어야 함을 뜻한다.
// 형식 매개 변수에 있는 +는 공변(covariant)를 뜻한다.
// 결국 하나의 동일한 정의로 List[Int], List[Double], List[String]등을 사용할 수 있게 된다.
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

  // 위 2 함수의 중복된 부분을 추출해서 함수 인수로 대체
//  foldRight(Cons(1, Cons(2, Cons(3, Nil))), Nil:List[Int])(Cons(_,_))
//  Cons(1, foldRight(Cons(2, Cons(3, Nil)), Nil:List[Int])(Cons(_,_)))
//  Cons(1, Cons(2, foldRight(Cons(3, Nil), Nil:List[Int])(Cons(_,_))))
//  Cons(1, Cons(2, Cons(3, foldRight(Nil, Nil:List[Int])(Cons(_,_)))))
//  Cons(1, Cons(2, Cons(3, Nil)))

  def foldRight[A,B](as: List[A], z: B)(f: (A,B) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  def sum2(ns: List[Int]) = foldRight(ns, 0)((x,y) => x + y)
  def product2(ns: List[Double])  = foldRight(ns, 1.0)(_*_)
  // _ * _ : (x,y) => x * y
  // _ * 2 : x => x * 2
  // _.head : x => x.head

  def length[A](l: List[A]): Int =
    foldRight(l, 0)((_,acc) => acc + 1)

  @annotation.tailrec
  def foldLeft[A,B](l: List[A], z: B)(f: (B, A) => B): B = l match {
    case Nil => z
    case Cons(h,t) => foldLeft(t, f(z,h))(f)
  }

  // foldLeft(Cons(1, Cons(2, Cons(3, Nil))), Nil:List[Int])(Cons(_,_))

  def sum3(l: List[Int]) = foldLeft(l, 0)(_ + _)
  def product3(l: List[Double]) = foldLeft(l, 1.0)(_ * _)

  def length2[A](l: List[A]): Int = foldLeft(l, 0)((acc,h) => acc + 1)

  def reverse[A](l: List[A]): List[A] = foldLeft(l, List[A]())((acc,h) => Cons(h,acc))

  // 함수가 A 형식의 인수를 0개 이상 받을 수 있음을 뜻한다.
  // 자료 형식을 만들 때에는, 자료 형식의 인스턴스를 편리하게 생성하기 위해 가변 인수 apply 메서드를 자료 형식의 동반 객체에 집어넣는 관례가 흔히 쓰인다.
  def apply[A](as: A*): List[A] = { // 가변 인수 함수 구문
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
  }

  val x = List(1,2,3,4,5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101
  }

  def tail[A](l: List[A]): List[A] = l match {
    case Nil => sys.error("tail of empty list")
    case Cons(_,t) => t
  }

  def setHead[A](l: List[A], h: A): List[A] = l match {
    case Nil => sys.error("setHead on empty list")
    case Cons(_, t) => Cons(h, t)
  }

  // tail을 일반화해서, 목록에서 처음 n개의 요소를 제거하는 함수를 구현
  def drop[A](l: List[A], n: Int): List[A] = {
      if (n <=0) l
      else l match {
        case Nil => Nil
        case Cons(_, t) => drop(t, n-1)
      }
  }

  // 주어진 술어와 부합하는 List의 앞 요소들을 제거하는 함수
  // 술어 부분의 (x: Int) => x < 4) 와 같이 Int를 명시적으로 표기해야 한다.
  // 첫번째 인수가가 List[Int]라면 두번째 인수도 반드시 Int를 받아야 한다.
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] =
    l match {
      case Cons(h,t) if f(h) => dropWhile(t, f)
      case _ => l
    }

  // 인수들을 아래와 같이 2그룹으로 묶으면 스칼라는 타입을 추론할 수 있다.
  def dropWhile2[A](as: List[A])(f: A => Boolean): List[A] = as match {
      // dropWhile2(t)는 하나의 함수를 돌려주며, 그 함수를 인스로 f를 호출한다.
      // 다른 말로 dropWhile2은 커링 되었따.
    case Cons(h,t) if f(h) => dropWhile2(t)(f)
    case _ => as
  }


  def append[A](a1: List[A], a2: List[A]): List[A] = {
    a1 match {
      case Nil => a2
      case Cons(h, t) => Cons(h, append(t, a2))
    }
  }

  @annotation.tailrec
  def startsWith[A](l: List[A], prefix: List[A]): Boolean = (l, prefix) match {
    case (_, Nil) =>  true
    case(Cons(h, t), Cons(h2, t2)) if  h == h2 => startsWith(t, t2)
    case _ =>  false
  }

//  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = sup {
//    case Nil => sub == Nil
//    case _ if startsWith(sup, sub) => true
//    case Cons(h, t) => hasSubsequence(t, sub)
//  }

  def main(args: Array[String]): Unit = {
//    println(List(1,2,3) match {case _ => 42}) // 42
//    println(List(1,2,3) match {case Cons(_, tail) => tail}) // Cons(2,Cons(3,Nil)) == List(2,3)
//    println(List(1,2,3)) // Cons(1,Cons(2,Cons(3,Nil)))
//    println(x) // 3
//    println(sum(List(1,2,3,4,5))) // 15
//    println(product(List(1,2,3))) // 6.0
//    println(tail(List(1,2,3))) // Cons(2,Cons(3,Nil))
//    println(setHead(List(1,2,3), 4)) // Cons(4,Cons(2,Cons(3,Nil)))
//    println(drop(List(1,2,3,4), 2)) // Cons(3,Cons(4,Nil))
//    println(dropWhile(List(1,2,3,4,5), (x: Int) => x < 4)) // Cons(4,Cons(5,Nil))
//    println(dropWhile2(List(1,2,3,4,5))(x => x < 4))
    println(startsWith(List(1,2,3,4,5), List(1,2,3)))
  }

}
