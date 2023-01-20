package revision

object Exception extends App {

  val x : String = null

  try {
      println(x.length) // this will throw NullPointer Exception
  } catch {
      case e1 : RuntimeException  => println("Run Time Exceptions live here ")
      case e2 : NullPointerException  => println("Null Pointer Exceptions live here")
  } finally{ // optional
      // executes always
      println("Finally block executes always")
  }

  def getInt(throwsException: Boolean) : Int ={
    if(throwsException) throw new RuntimeException("No Int for you!")
    else 42
  }

  try {
      getInt(true)
  } catch {
      case e : Exception => println("Int Exception caught here")
      case e : OutOfMemoryError => println("Out of Memory Error caught here")
  }

}
