package part2.OOP

object AnonymousClasses extends App {

  abstract class Anonymous{
    def  printHello = println("Hello in Abstract Class")
  }

  class newAnon extends Anonymous{
    override def printHello = println("Hello in the New Anonymous Class")
  }

  val anonymousClass = new Anonymous{
    override def printHello = println("Hello in the Anonymous Class")
  }
  println(anonymousClass.getClass)
  println(anonymousClass.getClass.getGenericSuperclass)

  val newAnon = new newAnon
  newAnon.printHello

  println(newAnon.getClass)
  println(newAnon.getClass.getGenericSuperclass)

}
