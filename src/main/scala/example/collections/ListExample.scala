package example.collections

object ListExample {
  type Name = String
  type Person = (String, Int)
  type FType = String => Int

  def main(args: Array[String]): Unit = {
    val name : Name = "Kun hong"
    val person : Person = ("Mr.kim", 24)
    val f: FType = text => text.toInt

    for (x <-1 until 5 ; y <-1 until 5) {
      print(x + " * " + y + " = " + x * y + " | ")
    }
    // 1 * 1 = 1 | 1 * 2 = 2 | 1 * 3 = 3 | 1 * 4 = 4 | 2 * 1 = 2 | 2 * 2 = 4 | 2 * 3 = 6 | 2 * 4 = 8 | 3 * 1 = 3 | 3 * 2 = 6 | 3 * 3 = 9 | 3 * 4 = 12 | 4 * 1 = 4 | 4 * 2 = 8 | 4 * 3 = 12 | 4 * 4 = 16 |


    println()

    for (i <-(1 to 10) if (i % 2 == 0)) {
      print(i + " ")
    }
    println()
    // 2 4 6 8 10

    // 리스트
    // List는 추상 클래스 형태 혹은 이미 완성된 객체 형태로 존재하기 때문에 보통의 클래스인 배열을 선언할 때와 달리 new를 쓰지 않습니다.
    // List 정적 객체의 내부적인 팩토리 역할인 apply() 메서드가 작동하면서 새로운 List 객체를 생성
    val list = List("a", "b", "c") // val list: List[String] = List("a", "b", "c")
    // 값을 꺼낼때는 배열과 동일한 방식이며 리스트의 apply() 메서드는 자바의 get()  메서드와 똑같이 동작하도록 기본적으로 구현되어 있음
    println(list(0)) // a

    // Nil : List를 상속받는 하나의 리스트, 아무것도 아닌 값으로서 리스트의 끝을 가리킴
    // cons(::) 연산자 -> construction
    val list2 = "a" :: "b" :: "c" :: Nil // 리스트에 속한 연사자임을 알아야 하므로 리스트의 끝이라는 점을 판단할 수 있도록 Nil을 명시

    // for 문에서 until 대신 to를 쓴다면 리스트의 크기를 초과한 곳에서 값을 읽으려 하기 때문에 IndexOutOfBoundsException 예외가 발생됨
    val list_empty : List[Int] = Nil

    for (x <-0 until list2.size)
      println(list2(x))
    // a
    // b
    // c

    // 여러 리스트를 병합하는 역할 -> ::: -> 평탄(flattern) 하게 하는 역할
    // :: -> 리스트의 각 요소를 결합
    val lst1 = "a" :: "b" :: "c" :: Nil
    val lst2 = "d" :: "e" :: Nil
    val lst3 = lst1 ::: lst2 // List.concat() 메서드와 동일 List.::: 와 같이 쓰는 것도 가능 (단 결합되는 순서가 반대)
    val lst4 = 1 :: 2 :: 3 :: Nil
    val lst5 = 2 :: 2 :: 4 :: Nil

    for (x <-0 until lst3.size)
      println(lst3 (x))

    // 리스트 주요 메서드
    // ++ -> ::: 동일
    // apply(int) -> List(i)
    // reverse, max, min, sum
    // mkString(String) -> 구분자 문자열을 받아서 리스트 내 모든 요소를 구분자로 구분한 문자열을 반환
    // exists(p: A => Boolean)
    // contains
    // isEmpty
    // distinct

    println("-----------------------------------")
    println(lst1++lst2) // List(a, b, c, d, e)
    println(lst1.apply(0)) // a
    println(lst4.reverse) // List(3, 2, 1)
    println(lst4.max) // 3
    println(lst4.min) // 1
    println(lst4.sum) // 6
    println(lst4.mkString(",")) // 1,2,3
    println(lst4.exists(a => a > 3)) // false
    println(lst4.exists(_ > 3)) // 위와 동일
    println(lst4.contains(1)) // true
    println(lst4.isEmpty) // false
    println(lst5.distinct) // List(2, 4)

    // 리스트나 배열 같은 컬렉션은 Traverable 상속을 받는데, 여기에 컬렉션에 필요한 많은 메서드를 포함한다.

    // Tip
    // 아래 표현운 모두 같은 뜻이다.
    // list.toString() == list.toString == list toString

  }
}
