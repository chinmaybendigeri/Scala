package part3.FunctionalProgramming

object FunctionsBasics extends App{

  val concatStr = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = {
    v1 + v2
    }
  }

  //println(concatStr("Chinmay"," Bendigeri"))

  //println(concatStr.getClass)

  val superFunction = new ((Int) => ((Int) => Int)) {
    override def apply(x: Int): Int => Int = new ((Int) => Int) {
      override def apply(y: Int): Int = x + y
    }
  }

  val adderFunc = superFunction(56)

  println(adderFunc)
  println(adderFunc(44))  // f(56) (44)
  //or

  println(superFunction(90)(10)) // this Is called a curried fucntion in scala ==> Syntactic Sugar

  val superLamdaFunction  = (x : Int) => (y : Int) => x + y
  println(superLamdaFunction(80)(10))

  val plusOne = (x : Int) => x + 1

  val x = plusOne(plusOne(1))
  println(x)
}
