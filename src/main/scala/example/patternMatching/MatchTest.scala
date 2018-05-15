package example.patternMatching

object MatchTest {
  def matchTest1(x: Int) : String = x match {
    case 1 => "one"
    case 2 => "two"
    case _ => "many"
  }



  def matchTestAny(x: Any) : Any = x match {
    case 1 => "one"
    case "two" => 2
    case y : Int => "scala.Int"
    case _ => "기타"
  }

  def main(args: Array[String]): Unit = {
    println(matchTest1(1)) // one
    println(matchTest1(2)) // two
    println(matchTest1(3)) // many

    val y = 10
    println(matchTestAny(y)) // scala.Int
    println(matchTestAny("two")) // 2
    println(matchTestAny(100.1)) // 기타
  }
}
