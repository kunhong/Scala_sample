package example.collections

object ArrayExample {
  def main(args: Array[String]): Unit = {
    val array = new Array[Int](10) //val array: Array[Int] = new Array[Int](10)
    val array_apply = Array(13, 42, 52, 15, 222) // Array.apply()와 동일하게 동작
    val array_string = Array("a", "v", "d", "c")

    for (str <- array_string)
      println(str)

    val arrayA = Array(14, "v", 1.42)

    for (x <- arrayA)
      println(x)

    // 다차원 배열
    val matrix = Array.ofDim[Int](4, 5) //  4행 5열인 이차원 배열 생성
    matrix(2)(3) // 2열 3행의 요소를 빼어옴
  }
}
