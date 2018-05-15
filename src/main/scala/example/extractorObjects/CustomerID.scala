package example.extractorObjects

import scala.util.Random

object CustomerID {
  def apply(name: String) = s"$name--${Random.nextLong}"

  def unapply(customerID: String): Option[String] = {
    val name = customerID.split("--").head
    if (name.nonEmpty) Some(name) else None
  }

  def main(args: Array[String]): Unit = {
    val customer1ID = CustomerID("Sukyoung")
    customer1ID match {
      case CustomerID(name) => println(name)   // Sukyoung
      case _ => println("Could not extract a CustomerID")
    }
  }
}
