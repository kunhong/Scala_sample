package example.collections

object SeqExample {
  def main(args: Array[String]): Unit = {
    // 시퀀스 커렉션은 리스트와 거의 같지만 요소 간 순서가 있다는 점이 다릅니다.
    // 내부적으로 인덱스에 대한 정보를 가지고 있으므로 인덱스와 관련해서 써야 할 기능이 많을 경우 쉽게 데이터를 다를 수 있습니다.

    val seq = Seq(1, 2, 3, 4, 5, 6, 7, 8, 9)

    println(seq.apply(2)) // 3

    // indexWhere : 요소(들)을 삭제하여 새로운 집합을 만들고 반환
    // startsWith
    // endsWith
    // indexOf // 해당 요소와 일치하는 요소의 인덱스를 반환
    // lastIndexOf : 해당 요소와 일치하는 요소가 여러 개일 때 마지막 인덱스를 반환

  }

}
