package example.regex

/*
* ^ : Matches beginning of line
* $ : Matches end of line
* . : Matches any single character except newline. using m option allows it ot match newline as well
* [...] : Matches any single character in brackets
* [^...] : Matches any single character not in brackets
* \\A : Beginning of entire string
* \\z : End of entire string
* \\Z : End of entire string except allowable final line terminator
*
* */
object Demo {
  def main(args: Array[String]): Unit = {
    val pattern = "Scala".r
    val str = "Scala is Scalable and cool"

    println(pattern findFirstIn str) // Some(Scala)
    println((pattern findAllIn str).mkString(",")) // Scala,Scala
    println(pattern replaceFirstIn(str, "Java")) // Java is Scalable and cool

    val pattern2 = "[0-9]".r
    val pattern2_str = "happy 1870 years and coming 1871"

    println(pattern2 findFirstIn pattern2_str) // Some(1)
    println((pattern2 findAllIn pattern2_str).mkString(",")) // 1,8,7,0,1,8,7,1

    val pattern3 = "[^0-9]".r
    val pattern3_str = "happy 1870 years and coming 1871"

    println(pattern3 findFirstIn pattern3_str) // Some(h)
    println((pattern3 findAllIn pattern3_str).mkString(",")) // h,a,p,p,y, , ,y,e,a,r,s, ,a,n,d, ,c,o,m,i,n,g,

    val pattern4 = s"\\d{3,}".r

    println(pattern4 findFirstIn pattern3_str) // Some(1870)
    println((pattern4 findAllIn pattern3_str).mkString(",")) // 1870,1871

    val uri = new java.net.URI("https://10.88.182.11:61616")
    println(uri.getHost) // 10.88.182.11
    println(uri.getScheme) // https
    println(uri.getPort) // 61616
  }

}
