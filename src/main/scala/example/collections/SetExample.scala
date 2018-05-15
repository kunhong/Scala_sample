package example.collections

object SetExample {
  def main(args: Array[String]): Unit = {
    var basket: Set[String] = Set()
    basket += "딸기"
    basket += "딸기"
    basket += "딸기"
    basket += "포도"

    println(basket) // Set(딸기, 포도)
  }

}
