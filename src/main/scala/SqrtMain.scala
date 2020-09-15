import scala.math.pow

object SqrtMain {

  // program entry point which accepts
  // arguments provided on the command line
  def main(args: Array[String]): Unit = {
    var number = -1.0
    var numStr = "(unspecified number)"
    try {
      numStr = args(0)
      number = dbl(numStr)
    }
    catch  {
      case _: Throwable => println("missing command line argument")
    }
    if (number == 0) {
      println("the square root of 0 is 0")
    }
    else if (number > 0) {
      val max_level = 15 // may be overkill, but speed doesn't seem to be a problem
      val estimate = Array(initial_estimate(number))

      // separate the output from the input with a blank line
      println()
      var root = square_root(number, estimate, max_level, 1, Newton)
      println(s"Using Newton's method, the square root of ${number} is ${adverb(number, root)} ${root}")

      // another blank line to improve readability
      println()
      // reset the initial estimate
      estimate(0) = initial_estimate(number)
      root = square_root(number, estimate, max_level, 1, Babylonian)
      println(s"Using the Babylonian method, the square root of ${number} is ${adverb(number, root)} ${root}")
    }
    else {
      // the program doesn't handle valid negative numbers, or anything that fails
      // to parse to a double, which is flagged as -1 in the dbl() function
      println("sorry, I don't how to find the square root of " + numStr)
      println("please supply a non-negative real number as the program argument")
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

  // helper function to pass to Scala's string interpolation
  def adverb (num: Double, est: Double): String = {
    if (est * est == num) { "" } else { "approximately" }
  }

  // help the methods converge for larger numbers
  def initial_estimate(number: Double): Double = {
    if (number > 1000) {
      number/10.0
    }
    else {
      1.0
    }
  }

  // recursive function to approximate square root using an iterative method
  // in_num: the number for which the square root is sought
  // estimate: a 1-element array that stores the current estimate
  // max_level: number of iterations of the method (i.e., number of recursive calls)
  // level: current level of recursion
  // estimate_reviser: function that produces estimate x[n] from x[n-1] and the input number
  def square_root(in_num: Double,
                  estimate: Array[Double],
                  max_level:Int,
                  level:Int,
                  estimate_reviser: (Double, Double) => Double
                 ): Double = {

    if (level == max_level) // recursion end condition
      estimate(0)
    else {
      // calculate new estimate using current estimate
      estimate(0) = estimate_reviser(in_num, estimate(0))
      // call recursively
      square_root(in_num, estimate, max_level, level + 1, estimate_reviser)
    }
  }

  // single iteration of Newton's Method
  // https://en.wikipedia.org/wiki/Newton%27s_method#Square_root
  def Newton(in_num: Double, prev_est: Double): Double = {
    prev_est - ((pow(prev_est,2) - in_num)/(2 * prev_est))
  }

  // single iteration of the Babylonian method
  // https://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Babylonian_method
  def Babylonian(in_num: Double, prev_est: Double): Double = {
    0.5 * (prev_est + in_num/prev_est)
  }
}
