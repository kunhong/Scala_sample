package example.collections

object MapExample {
  def main(args: Array[String]): Unit = {
    var map = Map (
      "number1" -> "aa",
      "number2" -> "bb",
      "number3" -> 3,
      "number4" -> "cc"
    )

    println(map.isEmpty) // false
    println(map) // Map(number1 -> aa, number2 -> bb, number3 -> 3, number4 -> cc)
    println(map.keys) // Set(number1, number2, number3, number4)
    println(map.values) // MapLike.DefaultValuesIterable(aa, bb, 3, cc)
    println(map("number1")) //aa

    // map 에 값을 추가
    map += ("number5" -> "this is the fifth value")

    // map 에서 값 삭제
    map -= "number5"

    var map2 = Map (
      1 -> "kim",
      2 -> "hong"
    )

    var map3 = map ++ map2

    map3.foreach( i => print(i) ) // (number1,aa)(number2,bb)(1,kim)(2,hong)(number4,cc)(number3,3)

    println()

    println(map3.contains(3)) // false

  }

}
