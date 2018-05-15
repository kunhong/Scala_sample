package example.singleton

class Email(val username: String, val domainName: String)

object  Email {

  // factory fromString which creates and Email instance from a String
  def fromString(emailString: String): Option[Email] = {
    emailString.split('@') match  {
      case Array(a, b) => Some(new Email(a, b))
      case _ => None
    }
  }

  def main(args: Array[String]): Unit = {
    val scalaCenterEmail = Email.fromString("scala.center@epfl.ch")
    scalaCenterEmail match {
      case Some(email) => println(
        s"""Registered an email
           |Username: ${email.username}
           |Domain name: ${email.domainName}
     """)
      case None => println("Error: could not parse email")
    }
  }
}