package example.patternMatching

object CaseClassMatchTest {
  case class Person(name: String, age: Int, rank: String)

  def main(args: Array[String]): Unit = {
    val person1 = Person("kun", 47, "admin")
    val person2 = Person("lim", 23, "chief")
    val person3 = Person("park", 76, "bose")
    val person4 = Person("철수", 18, "사원")

    matchAndHi(person1)
    matchAndHi(person2)
    matchAndHi(person3) // bose welcome to korea!!!
    matchAndHi(person4) // 철수 18살았나!!! 선 볼래???
  }

  def matchAndHi(person: Person): Unit = person match {
    case Person("kun", 47, "admin") => println("kun!!!")
    case Person("lim", 23, "chief") => println("lim!!!")
    case Person("park", 76, "zzang") => println("park!!!")
    //case Person("park", 76, _) => println("park!!!")
    case Person("park", 76, r) => println(r + " welcome to korea!!!")
    case Person(name, age, "사원") if age < 20 => println(name + " " + age + "살았나!!! 선 볼래???")
  }
}
