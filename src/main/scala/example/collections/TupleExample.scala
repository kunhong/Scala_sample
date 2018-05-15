package example.collections

object TupleExample {
  def main(args: Array[String]): Unit = {
    var tuple = (10, "apple")

    println(tuple._1) // 10
    println(tuple._2) // apple

    println(tuple.swap) // (apple,10)

  }
}
