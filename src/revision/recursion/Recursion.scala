package revision.recursion

object Recursion extends App{

  // concatenate string n times

  def strConcate(input: String = "hello", ntimes : Int, accumStr : String = "") : String = {
      if(ntimes==0) accumStr
      else
        strConcate(input, ntimes-1,input+accumStr)
  }

  println(strConcate("Chinmay",5))

  val anyVal = "2"
  println(anyVal)
  println('a' +: anyVal :+ 'z')

  val name = "Chinmay"
  val age = 27

  val printString = s"Hello, My name is $name and i am ${age+1} years old"
  println(printString)

  val speed = 322.643f
  val myMyth = f"$name ca eat at $speed%7.4f"
  println(myMyth)
}
