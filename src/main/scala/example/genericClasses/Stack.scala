package example.genericClasses

class Stack[A] {
  private var elements: List[A] = Nil
  def push(x: A) { elements = x :: elements }
  def peek: A = elements.head
  def pop(): A = {
    val currentTop = peek
    elements = elements.tail
    currentTop
  }
}

class Fruit
class Apple extends Fruit
class Banana extends Fruit

object StackTest extends App {
  val stack = new Stack[Int]
  stack.push(1)
  stack.push(2)
  println(stack.pop)
  println(stack.pop)

  val stack_fruit = new Stack[Fruit]
  val apple = new Apple
  val banana = new Banana
  stack_fruit.push(apple)
  stack_fruit.push(banana)
}
