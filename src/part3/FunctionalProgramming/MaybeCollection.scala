package part3.FunctionalProgramming


object MaybeCollection extends App {

  abstract class  Maybe[+T]{
    def getFirst : T
    def isFilled : Boolean
    def map[B](function: T => B): Maybe[B]
    def printElements : String
    override def toString: String = "["+ printElements + "]"
  }

  object MaybeNot extends Maybe[Nothing]{

    def getFirst : Nothing = throw new RuntimeException

    def isFilled: Boolean = false

    def map[B](function: Nothing => B): Maybe[B] = MaybeNot

    def printElements: String = ""
  }

  class Just[+T](val e : T) extends Maybe[T] {

    def getFirst : T = e

    def isFilled: Boolean = true

    def map[B](function: T => B): Maybe[B] = {
      new Just(function.apply(e))
    }

    def printElements: String = {
      ""+e
    }
  }


  object MaybeCollectionRunner{
    val list = new Just[Int](3)
    //println(list.getFirst)

    println(list.map(x => x * 2))

    println(for {
      x <- list
    } yield x * 3)

    val emptyList = MaybeNot
    //println(emptyList.map(x => x * 2))

  }

  MaybeCollectionRunner

}
