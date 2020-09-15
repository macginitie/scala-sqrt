import scala.math.pow

object SqrtMain {

  // program entry point which accepts
  // arguments provided on the command line
  def main(args: Array[String]): Unit = {
    val s = args(0)
    val num = dbl(s)
    if (num == 0) {
      println("the square root of 0 is 0")
    }
    else if (num > 0) {
      val max_level = 12
      var estimate = Array(1.0)

      val root = square_root(num, estimate, max_level, 1, Newton)
      println(s"the square root of ${num} is approximately ${root}")
    }
    else {
      // the program doesn't handle valid negative numbers, or anything that fails
      // to parse to a double, which is flagged as -1 in the dbl() function
      println("sorry, I don't how to find the square root of " + s)
      println("please restrict input to digits, with at most one decimal point")
      println("(this program doesn't handle negative numbers,")
      println("but valid scientific notation is accepted)")
    }
  }

  // parse s to extract Double;
  // returns -1.0 to indicate failure to parse,
  // so that negative numbers and invalid arguments
  // can be handled with the same code
  def dbl(s: String): Double = {
    s.toDoubleOption.getOrElse(-1d)
  }

  // recursive function to approximate square root using an iterative method
  // in_num: the number for which the square root is sought
  // estimate: a 1-element array that stores the current estimate
  // max_level: number of iterations of the method (i.e., number of recursive calls)
  // level: current level of recursion
  // estimate_reviser: function that produces estimate x[n] from x[n-1] and the input number
  def square_root(in_num: Double,
                  estimates: Array[Double],
                  max_level:Int,
                  level:Int,
                  estimate_reviser: (Double, Double) => Double
                 ): Double = {
    // useful for debugging:
    // println(s"${level} ==> ${estimate(0)}, ${estimate(1)}")
    if (level == max_level)
      estimates(0)
    else {
      // calculate new estimate using previous estimate
      var prev_est = estimates(0)
      estimates(0) = estimate_reviser(in_num, prev_est)
      square_root(in_num, estimates, max_level, level + 1, estimate_reviser)
    }
  }

  // single iteration of Newton's Method
  def Newton(in_num: Double, prev_est: Double): Double = {
    prev_est - ((pow(prev_est,2) - in_num)/(2 * prev_est))
  }
}
