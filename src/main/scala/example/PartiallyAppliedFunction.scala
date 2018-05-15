package example

object PartiallyAppliedFunction {
  def main(args: Array[String]): Unit = {
    val thisYear = 2018

    // 아직 String 에 해당하는 인수가 존재하지 않지만
    // _ 와일드카드를 사용해 어느 String 값이든 들어올 수 있다고 선언
    val fixedValueFunction = go(thisYear, _: String)

    fixedValueFunction("test1")
    fixedValueFunction("test2")
    fixedValueFunction("test3")

    // 커링을 이용한 부분 적용 함수
    // currying 함수는 함수형 프로그래밍 언어에 자주 나오는 용어입니다. 커링이란 여러 개의 인수를 받는 하나의 함수를 하나의 인수를 받은 여러 개의 함수로 바꿔주는 기법을 뜻합니다.
    def normalSum(x: Int, y: Int) = x + y
    def curryingSum(x: Int)(y: Int) = x + y


    val fixedValueCurryingFunction = go_currying(thisYear)_
    fixedValueCurryingFunction("test4")
    fixedValueCurryingFunction("test5")
    fixedValueCurryingFunction("test6")

  }

  def go(thisYear: Int, string: String) = {
    println(string + ":" + thisYear)
  }

  def go_currying(thisYear: Int)(string: String) = {
    println(string + ":" + thisYear)
  }
}
