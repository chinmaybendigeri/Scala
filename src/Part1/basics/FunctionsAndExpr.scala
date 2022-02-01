package Part1.basics

object FunctionsAndExpr {

  def main(args: Array[String]) = {

    def aNewFunction(a: String, b: Int) = 42

    def otherFunction = {
      println("this returns Integer Type and has no side effect")
      0
    }

    println(otherFunction)

    println(aNewFunction("Hello World!", 100))

    val x = println("This returns Unit type and has a side effect")

    println(x)

    def factFunc(x: Int): Int = {
      if (x == 1) 1
      else {
        x * factFunc(x - 1)
      }
    }

    println(factFunc(10))

    def fibnocciFunc(x: Int): Int = { // 0,1,1,2,3,5,8,13 ...
      if (x == 1) 1
      else if (x == 2) 1
      else {
        fibnocciFunc(x - 1) + fibnocciFunc(x - 2)
      }
    }

    println(fibnocciFunc(7))

    def isPrime(x: Int): Boolean = {
      var divisor = x / 2
      var isPrime = true
      while (divisor > 1) {
        if (x % divisor == 0) {
          isPrime = false
        }
        divisor = divisor - 1
      }
      isPrime
    }

    println(isPrime(37))

  }


}
