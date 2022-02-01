package part4.PatternMatching

import scala.util.Random

object PMForValueAndCaseClass extends App {

  // Scala provides PM functionality which are similar to switch statements
  // in other languages but much more powerful than them
  // with PM , we can not only check the value but also can check if the passed parameter
  // is an instance of some case classes


  //1. Use PM as Switch , to check Value
  val r = new Random()
  val x = r.nextInt(10)

  val some = x match {
    case 1 => "Lone wolf"
    case 2 => "Beautiful couple"
    case 3 => "ThreeSome"
    case _ => "Fuck off!!"   // default in Scala
  }

  println(x)
  println(some) // return type of some here would the unified value for all the case types

  //2. PM with Case Classes

  case class Person(name: String, age : Int)  
  
  val p = Person("Chinmay",26)

  val person = p match {
    case Person(someName, someAge) => s"Hi , I am $someName and my age is $someAge"
    case _ => s"Hi, I dont know who i am these days!!"
  }

  println(person)

  //Exercise
  // (1 + 2) * 3 => Prod(Sum(Number(1),Number(2)),Number(3))
  //  3 * 2 + 3  => Sum(Prod(Number(3),(Number(2)),Number(3))

  trait Expr
  case class Number(x: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def showResultantExpr(expr: Expr): String = expr match{
    case Number(expr) => s"$expr"
    case Sum(e1, e2) => showResultantExpr(e1) +" + "+ showResultantExpr(e2)
    case Prod(e1, e2) => {
      def showParanthesisMaybe(e: Expr) :String = e match {
        case Number(_) => showResultantExpr(e)
        case Prod(_, _) => showResultantExpr(e)
        case _ => "(" + showResultantExpr(e)  + ")"
      }
     showParanthesisMaybe(e1) + " * " + showParanthesisMaybe(e2)
    }
  }

  println(showResultantExpr(Prod(Sum(Number(1),Number(2)),Number(3))))
  println(showResultantExpr(Sum(Prod(Number(3),Number(2)),Number(3))))


}
