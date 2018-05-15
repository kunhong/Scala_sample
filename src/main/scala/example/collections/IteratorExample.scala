package example.collections

object IteratorExample {
  def main(args: Array[String]): Unit = {
    val list = List("a", "b", "c")
    val iter = list.iterator

    while(iter.hasNext)
      println(iter.next)
  }
}
