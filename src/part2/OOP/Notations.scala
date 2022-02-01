package part2.OOP

object Notations extends App{

  class Person(val name: String, favMovie : String, val age : Int = 0){


    def +(nickName : String): Person = {
      println("inside + Overloaded + function")
      new Person(name + nickName,favMovie)
    }

    def + : String = {
      val maryWithNic = new Person(name ,favMovie)
      println("inside + Overloaded with no parameters + function")
      maryWithNic.name
    }

    def unary_+ : Person = {
      new Person(name,favMovie,age + 1)
    }

    def learns(learn : String): Unit ={
      println(s"$name learns $learn")
    }

    def learnScala: Unit ={
      println(s"$name is learning Scala")
    }

    def apply(): Unit = println(s"Hi I am $name and i like $favMovie")

    def apply(otherfavMovie : String) = println(s"Hi I am $name and i like $otherfavMovie")
  }

  val mary = new Person("Mary","Inception")
  println((mary + " (the rockstar)").apply)    // Infix operation    -----> can be used only when there is one parameter to a method

  println(mary+)     // Syntactic Sugar ---> Postfix expression ---> function should not have any parameters

  //prefix expression
  println((+mary).age)  // unary expressions methods can only be applied to +,-,~,!

  mary learns "Scala"    // Another example for infix expression
  mary learnScala        // Example for postfix expression

  mary()              // call apply method in Person class
  mary("Fight Club")  // call overloaded apply method in Person class
}
