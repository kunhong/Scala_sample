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
    // Password must contain a number

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



//    scala> val input = "Enjoying this apple 3.14159 times today"
//    input: String = Enjoying this apple 3.14159 times today
//
//    scala> val pattern = """.* apple ([\d.]+) times .*""".r
//    pattern: scala.util.matching.Regex = .* apple ([\d.]+) times .*
//
//    scala> val pattern(ammountText) = input
//    ammountText: String = 3.14159
//
//    scala> val amount = ammountText.toDouble
//    amount: Double = 3.14159

    //6) Using the input string "Frank,123 Main,925-555-1943,95122" and regular expression matching, retrieve the telephone number.
    // Can you convert each part of the telephone number to its own integer value? How would you store this in a tuple?

    // 입력된 문자열 "Frank,123 Main,925-555-1943,95122"와 정규 표현식 매칭 기법을 사용하여 전화번호를 추출해 보자.
    // 전화번호의 각 부분을 정수 숫자로 전환할 수 있는가?
    // 이 숫자를 어떻게 하나의 튜플에 저장할 수 있을까?
    val s = "Frank,123 Main,925-555-1943,95122"
    val p = """.*,(\d{3})-(\d{3})-(\d{4}),.*""".r
    val p(p1,p2,p3) = s
    val phoneNumber = (p1.toInt, p2.toInt, p3.toInt)
    println(phoneNumber)




  }
}
