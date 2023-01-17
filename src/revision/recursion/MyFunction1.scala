package revision.recursion

object MyFunction1 extends App{

  val concateFunc = new Function2[String, String, String] {
    override def apply(x: String , y: String) = x + y
  }
  println(concateFunc("Chinmay","Bendigeri"))

  val anotherFunc = new ((Int) => (Int) => (Int)){
    def apply(x : Int): (Int) => (Int) = new ((Int) => Int){
      def apply(y: Int) : Int = x + y
    }
  }


  val test = anotherFunc(50)(100) // these are curried functions

  println(anotherFunc(50)(100))

}
