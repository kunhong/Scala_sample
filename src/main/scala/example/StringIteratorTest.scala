package example

object StringIteratorTest {
  def main(args: Array[String]): Unit = {
    class Iter extends StringIterator("Scala") with RichIterator
    val iter = new Iter
    iter foreach println
  }
}
