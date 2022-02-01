package Part1.basics

import scala.annotation.tailrec

object TailRecursion extends App {

  // Print a Sting n times using normal recursion

  def printNtimes(str: String, n: Int): String = {
    if (n == 1) str
    else
      str + printNtimes(str, n - 1)
  }

  // println(printNtimes("HelloWorld ",100000))

  @tailrec
  def printNtimesTail(n: Int, singleStr: String, accumulatedStr: String): String = {
    if (n <= 0) accumulatedStr
    else {
      println("This is " + n + "th call and the accumalated String is " + accumulatedStr)
      printNtimesTail(n - 1, singleStr, accumulatedStr.concat(singleStr))
    }
  }

  println(printNtimesTail(3, "HelloWorld ", ""))
}
