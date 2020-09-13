object SqrtMain {

  val max_level = 5
  var answers = Array(1.0,0,0,0,0)

  def main(args: Array[String]): Unit = {
    val s = args(0)
    val num = dbl(s)
    if (num == 0) {
      println("the square root of 0 is 0")
    }
    else if (num > 0) {
      val root = Newton(num, answers, max_level, 1)
      println(s"the square root of ${num} is approximately ${root}")
    }
    else {
      // complain politely
      println("sorry, I don't how to find the square root of "+args(0))
      println("please restrict input to digits, with at most one decimal point")
      println("(this program doesn't handle complex numbers, but valid scientific notation is accepted)")
    }
  }

  // parse s to extract Double;
  // return -1.0 to indicate failure
  def dbl(s: String): Double = {
    s.toDoubleOption.getOrElse(-1d)
  }

  def Newton(in_num: Double, answers: Array[Double], max_level:Int, level:Int): Double = {
    if (level == max_level) {
      answers(max_level-1)
    }
    else {
      // 2DO
      // calculate answer(level) using answer(level-1)
      Newton(in_num, answers, max_level, level + 1)
    }
  }
}
