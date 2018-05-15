package example.patternMatching

// 만약 단순한 문자열이나 숫자를 받았을 때 이 문자열을 가공해서 패턴 매칭을 하고 싶다면 추출자(extractor)를 사용하면 된다.
// 추출자는 대상 값을 가져와서 필요한 값을 추출한 후 가공해서 내보낼 수 있다.
object ExtractorMatchTest {
  def main(args: Array[String]): Unit = {
    val number1 = "010-1234-5678"
    val number2 = "119"
    val number3 = "포도먹은가즈야씨"
    val numberList = List(number1, number2, number3)

    // apply() : 생성자로 작용 시 여러 데이터를 ㅏㅂㄷ아 하나의 인스턴스를 만드는 데 일조하는 메서드
    // unapply() : apply()의 반대 기능, 하나로 뭉쳐진 데이터에서 필요한 값들을 따로 추출할 수 있는 기능
    //             매개변수는 상관없으나 반환 자료형이 Option 형티 및 Boolean이 되야 함
    //             반환값이 Some 혹은 true / None 혹은 false

    for (number <- numberList) {
      number match {
        case Emergency() => println("긴급전화 입니다.")
        case Normal(number) => println("일반전화 입니다. - " + number)
        case _ => println("판단할 수 없습니다.")
      }
    }
  }

  object Emergency {
    def unapply(number: String): Boolean = {
      if (number.length == 3 && number.forall(_.isDigit)) true
      else false
    }
  }

  object Normal {
    def unapply(number: String): Option[Int] = {
      try {
        var o = number.replaceAll("-", "")
        Some(number.replaceAll("-","").toInt)
      } catch {
        case _: Throwable => None
      }
    }
  }
}
