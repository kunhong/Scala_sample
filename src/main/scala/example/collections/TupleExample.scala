package example.collections

object TupleExample {
  def main(args: Array[String]): Unit = {
    var tuple = (10, "apple")

    println(tuple._1) // 10
    println(tuple._2) // apple

    println(tuple.swap) // (apple,10)

    val red = "red" -> "0xff0000"
    //red: (String, String) = (red,0xff0000)
    val reversed = red._2 -> red._1
    println(reversed) // (0xff0000,red)

  }
}
