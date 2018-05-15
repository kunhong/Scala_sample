package example

object ApplyFunctionTest {
  class SomeClass {
    // apply() 메서드를 이용하면 생성자처럼 초기화하거나 클래스 안에 특정한 메서드를 기본 메서드로 지정하는 것을 편하게 할 수 있다.
    def apply(m: Int) = method(m)
    def method(i: Int) = i + i
    def method2(s: String) = s
  }

  def main(args: Array[String]): Unit = {
    val something = new SomeClass

    println(something(2)) // 4
    // something(2) == something.apply(2) == something.method(2)
  }
}
