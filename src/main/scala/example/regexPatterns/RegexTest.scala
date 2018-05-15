package example.regexPatterns
import scala.util.matching.Regex

object RegexTest {

  val numberPattern: Regex = "[0-9]".r
  val keyValPattern: Regex = "([0-9a-zA-Z-#() ]+): ([0-9a-zA-Z-#() ]+)".r

  def main(args: Array[String]): Unit = {
    numberPattern.findFirstMatchIn("awesomepassword") match {
      case Some(_) => println("Password OK")
      case None => println("Password must contain a number")
    }

    val input: String =
      """background-color: #A03300;
        |background-image: url(img/header100.png);
        |background-position: top center;
        |background-repeat: repeat-x;
        |background-size: 2160px 108px;
        |margin: 0;
        |height: 108px;
        |width: 100%;""".stripMargin

    for (patternMatch <- keyValPattern.findAllMatchIn(input))
      println(s"key: ${patternMatch.group(1)} value: ${patternMatch.group(2)}")

    /*
    * key: background-color value: #A03300
      key: background-image value: url(img
      key: background-position value: top center
      key: background-repeat value: repeat-x
      key: background-size value: 2160px 108px
      key: margin value: 0
      key: height value: 108px
      key: width value: 100
    *
    * */
  }
}
