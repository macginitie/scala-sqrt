import scala.math
import scala.math.pow

object SqrtMain {

  def main(args: Array[String]): Unit = {
    val s = args(0)
    val num = dbl(s)
    if (num == 0) {
      println("the square root of 0 is 0")
    }
    else if (num > 0) {
      val max_level = 15
      var estimates = Array(1.0, 0.0)

      val root = Newton(num, estimates, max_level, 1)
      println(s"the square root of ${num} is approximately ${root}")
    }
    else {
      // complain politely
      println("sorry, I don't how to find the square root of " + s)
      println("please restrict input to digits, with at most one decimal point")
      println("(this program doesn't handle complex numbers, \nbut valid scientific notation is accepted)")
    }
  }

  // parse s to extract Double;
  // return -1.0 to indicate failure
  def dbl(s: String): Double = {
    s.toDoubleOption.getOrElse(-1d)
  }

  def Newton(in_num: Double, estimates: Array[Double], max_level:Int, level:Int): Double = {
    println(s"${estimates(0)}, ${estimates(1)}")
    if (level == max_level)
      estimates(1)
    else {
      // 2DO
      // calculate new estimate using previous estimate
      var prev_est = estimates(0)
      estimates(1) = prev_est - ((pow(prev_est,2) - in_num)/(2 * prev_est))
      estimates(0) = estimates(1)
      Newton(in_num, estimates, max_level, level + 1)
    }
  }
}
