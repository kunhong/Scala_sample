package example

class Point(xc : Int, yc : Int) extends Similarity {
  var x : Int = xc
  var y : Int = yc


  override def isSimilar(obj: Any): Boolean = obj.isInstanceOf[Point] && obj.asInstanceOf[Point].x == x

  def move(dx: Int, dy : Int): Unit = {
    x += dx
    y += dy
  }

  override def toString: String = "(" + x + ", " + y + ")"
}
