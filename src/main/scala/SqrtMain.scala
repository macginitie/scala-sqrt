object SqrtMain {

  def main(args: Array[String]): Unit = {
    for (s <- args) {
      println(s)
    }
    println(s"the square root of "+args(0)+" is approximately 2")
  }

  def Newton(num: Double): Double = {
    2.0
  }
}
