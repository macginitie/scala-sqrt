object SqrtMain {

  def main(args: Array[String]): Unit = {
    val s = args(0)
    if (check_arg(s)) {
      val num = dbl(s)
      val root = Newton(4.0)
      println(s"the square root of "+args(0)+s" is approximately ${root}")
    }
    else {
      // 2DO: complain politely
      println("sorry, dunno how to find the square root of "+args(0))
      println("please restrict input to digits, with at most one decimal point")
    }
  }

  def check_arg(s: String): Boolean = {
    // 2DO
    true
  }

  def dbl(s: String): Double = {
    // 2DO
    4.0
  }

  def Newton(num: Double): Double = {
    // 2DO
    2.0
  }
}
