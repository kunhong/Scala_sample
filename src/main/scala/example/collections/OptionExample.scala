package example.collections

object OptionExample {
  def main(args: Array[String]): Unit = {
    val students = Map (
      1 -> "세은이",
      2 -> "정팔이",
      3 -> "정봉이"
    )

    val one = students.get(1)
    val four = students.get(4)

    println(one) // Some(세은이)
    println(four) // None
    println(one.get) // Some() 으로 래핑된 값을 벗겨서 반환 // 세은이
    println(four.getOrElse("값이 없습니다.")) // 값이 없습니다.
  }

}
