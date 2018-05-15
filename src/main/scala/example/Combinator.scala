package example

object Combinator {
  def main(args: Array[String]): Unit = {
    // map()
    // map[B](f: (A) => B): List[B]
    // map() 리스트 자체를 변형하지 않고 List 자료형을 반환
    val o = List(1, 2, 3, 4)
    val n = o.map(i => i * 10) // == o.map(_ * 10)
    println(n) // List(10, 20, 30, 40)

    // foreach
    n.foreach( i => i * 10)

    // filter
    val f = n.filter(_ >= 30).map(_ * 2)
    println(f) // List(60, 80)

    // foldLeft, foldRight
    // 컬렉션에 있는 여로 요소를 한쪽 방향으로 접어서 새로운 단 하나의 요소로 바꾸라는 말
    // 두 개의 요소를 가져다가 하나로 만드는 연속적인 과정
    // 이 과정을 단계적으로 해서 최종적으로 하나의 값이 나올 때까지 계산하도록 짜여 있는 것
    val a = List(1,2,3,4)
    val b = a.foldLeft(0)((i, j) => i + j) // 계속 컬렉션을 돌면서 두 개씩 요소를 비교합ㄴ다.
    // 각각의 요소를 i 와 j로 받으며, 이 코드의 경우 둘을 더해서 하나의 값으로 만들고 있다.
    // 초기 값 z는 0
    println(b) // 10

    // partition
    // 컬렉션을 나누는 기능
    val par = a.partition( i => i <3)
    println(par) // (List(1, 2),List(3, 4))


    // :::, zip(), unzip()
    // zip()은 지퍼를 올리는 것과 마찬가지로 두 개의 리스트를 하나로 합친다.
    // 다만 :::과는 달리 튜플 형태의 쌍으로 연결된 리스트를 만듬
    val oo = List(5,6,7,8,9)

    val nn = a zip oo
    val nnn = a ::: oo

    println(nn) // List((1,5), (2,6), (3,7), (4,8))
    println(nnn) // List(1, 2, 3, 4, 5, 6, 7, 8, 9)

    //find()
    println(nnn.find(_ == 9)) // Some(9)

    // drop(), dropWhile()
    // 원하는 요소를 떨궈내는, 즉 버리는 함수
    // dropWhile : 전체를 검색하지 않고 차례대로 검색하다가 조건이 맞지 않은 순산 기능을 멈춤
    val list = List(1,2,3,4,5,6,0)

    val _drop = list.drop(4) // List(5, 6, 0)
    val _dropwhile = list.dropWhile(i => i < 3) // List(3, 4, 5, 6, 0)

    println(_drop)
    println(_dropwhile)

    // flatten()
    // 리스트 안에 리스트가 중첩되는 상황 이러한 고인 상태를 풀어주는 기능
    val nestedlist = List(List(1,2,3,4), List(5,6))
    println(nestedlist.flatten) // List(1, 2, 3, 4, 5, 6)
  }
}
