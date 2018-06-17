package example

object LoopTest extends App {
  // to :끝을 포함하는 정수
  // until : 끝을 나타내는 정수를 포함하지 않는 리스트를 만듬

  // 기본 for 루프로 반복하기
  // for (<식별자> <- <반복자>) [yield] [<표현식>]
  // yield 는 선태갓항으로 표현식과 함께 쓰인다면 호출된 모든 표현식의 반환값은 컬렉션으로 반환된다.
  // 만약 yield 없이 표현식이 기술되어 있다면 그 표현식이 호출되기는 하지만, 그 반환값에는 접근할 수 없다.

  for(x <- 1 to 7) {println(s"Day $x:")} // 1 ~ 7
  val res0 = for(x <- 1 to 7) yield {(s"Day $x:")}
  // REPL 출력 결과
  // res0: scala.collection.immutable.IndexedSeq[String] = Vector(Day 1:, Day 2:, Day 3:, Day 4:, Day 5:, Day 6:, Day 7:)

  for (day <- res0) print(day + ", ")
  // Day 1:, Day 2:, Day 3:, Day 4:, Day 5:, Day 6:, Day 7:,

  // 반복자 가드
  val threes = for(i <- 2 to 20 if i % 3 == 0) yield i
  // threes: scala.collection.immutable.IndexedSeq[Int] = Vector(3, 6, 9, 12, 15, 18)

  val quote = "A,B,,C"
  for{
    t <- quote.split(",")
    if t != null
    if t.size > 0
  }
    {
      println(t)
    }
  // A
  // B
  // C

  // 중첩된 반복자 (nested iterator)
  for {
    x <- 1 to 2
    y <- 1 to 3
  } print(s"($x,$y) ") // (1,1) (1,2) (1,3) (2,1) (2,2) (2,3)
  println()


  // 값 바인딩 ( value binding)
  val powersOf2 = for (i <- 0 to 8; pow =  1 << i) yield  pow
  // powersOf2: scala.collection.immutable.IndexedSeq[Int] = Vector(1, 2, 4, 8, 16, 32, 64, 128, 256)

  var x = 10; while (x > 0) x -= 1
  x = 0
  do println(s"Here I am, x = $x") while (x > 0) // Here I am, x = 0

  for (i <- 1 to 100 by 5) {
    print(s"$i, ") // 1, 6, 11, 16, 21, 26, 31, 36, 41, 46, 51, 56, 61, 66, 71, 76, 81, 86, 91, 96, 
  }

//  1) Given a string name, write a match expression that will return the same string if non-empty, or else the string "n/a" if it is empty.
//
//    Answer
//
//  You could use a pattern guard here and check if the size of the string is zero or non-zero. However, it’s easier to just match based on an empty string.
//
//  scala> val name = "Dresden"
//  name: String = Dresden
//
//  scala> name match {
//  |   case "" => "n/a"
//    |   case n => n
//    | }
//  res0: String = Dresden
//  2) Given a double amount, write an expression to return "greater" if it is more than zero, "same" if it equals zero, and "less" if it is less than zero. Can you write this with if..else blocks? How about with match expressions?
//
//  Answer
//
//  First, here’s the if..else block (specifically an if..else-if..else block).
//
//  scala> val amount = 2.143
//  amount: Double = 2.143
//
//  scala> if (amount > 0) "greater" else if (amount < 0) "less" else "same"
//  res0: String = greater
//  Second, the match expression that achieves the same purpose.
//
//    scala> amount match {
//  |   case x if x > 0 => "greater"
//      |   case x if x < 0 => "less"
//      |   case x => "same"
//      | }
//  res1: String = greater
//  Which solution do you prefer? I find the match expression a bit more readable but the if..else block is certainly more compact.
//
//  3) Write an expression to convert one of the input values "cyan", "magenta", "yellow" to their 6-char hexadecimal equivalents in string form. What can you do to handle error conditions?
//
//  Answer
//
//  Returning an appropriate hexadecimal version of one of the three possible color names is easy enough. A good error handling solution should check if an unexpected color name was given and log the error, while still returning a useful color.
//
//    scala> val color = "magenta"
//  color: String = magenta
//
//  scala> color match {
//  |   case "cyan" => "00ffff"
//    |   case "magenta" => "00ff00"
//    |   case "yellow" => "ffff00"
//    |   case x => {
//    |     println(s"Didn't expect $x !")
//    |     "333333"
//    |   }
//    | }
//  res0: String = 00ff00
//    4) Print the numbers 1 to 100, with each line containing a group of 5 numbers. For example:
//
//    1, 2, 3, 4, 5,
//  6, 7, 8, 9, 10
//  ....
//  Answer
//
//  Two for-loops, one for the rows and one for the columns, can handle this task.
//
//  scala> for (i <- 1 to 100 by 5) {
//    |   for (j <- i to (i + 4)) { print(s"$j, ") }
//    |   println("")
//    | }
//  1, 2, 3, 4, 5,
//  6, 7, 8, 9, 10,
//  11, 12, 13, 14, 15,
//  ...
//  5) There is a popular coding interview question I’ll call "typesafe", in which the numbers 1 - 100 must be printed one per line. The catch is that multiples of 3 must replace the number with the word "type", while multiples of 5 must replace the number with the word "safe". Of course, multiples of 15 must print "typesafe".
//
//  Answer
//
//  There’s a number of ways to solve this. Here’s a solution that isn’t very compact but plainly checks for the greatest factor (15) before checking for 5 and 3.
//
//  scala> for (i <- 1 to 100) {
//    |   i match {
//    |     case x if x % 15 == 0 => println("typesafe")
//      |     case x if x % 5 == 0 => println("safe")
//      |     case x if x % 3 == 0 => println("type")
//      |     case x => println(x)
//      |   }
//    | }
//  1
//  2
//  type
//  4
//  safe
//  ...
//  6) Can you rewrite the answer to question 5 to fit on one line? It probably won’t be easier to read, but reducing code to its shortest form is an art, and a good exercise to learn the language.
//
//  Answer
//
//  Note: in the first edition of the book the text says "answer to question 6", which is accidentally recursive. It should have said "answer to question 5".
//
//    To make this fit on a single line, I switched to a mutable string which could hold "type" and "safe". It can also be checked for emptiness and thus print the current integer value.
//
//  scala> for (i <- 1 to 100) { var s = ""; if (i%3==0) s="type"; if (i%5==0) s+="safe"; if(s.isEmpty) s += i; println(s) }
//  1
//  2
//  type
//  4
//  safe
//  type
//  7
//  8
//  Can you write a shorter version?

}
