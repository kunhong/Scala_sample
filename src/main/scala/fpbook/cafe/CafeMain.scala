package fpbook.cafe

object CafeMain {
  val f: (Any) => String = {
    case i: Int => "Int"
    case d: Double => "Double"
    case _ => "Other"
  }

  def main(args: Array[String]): Unit = {
    val isEvenVal = (i: Int) => i % 2 == 0
    def isEvenDef(i: Int) = i % 2 == 0

    println(f(1))
    println(f(1.2))

    val add = (a: Int, b: Int) => a + b

    println(add.toString())
  }

}
