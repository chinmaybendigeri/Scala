package revision

object OptionsEx extends App{

  def unsafeMethod() : String = null

  //println(unsafeMethod().length) // this will throw NullPointerException

  def safemethod() : String = {
    println("This is good!!!")
    "test"
  }

  val test = Option(unsafeMethod()).orElse(Option(safemethod()))

  def betterUnsafeMehod : Option[String] = null // this will return none
  def betterSafeMethod: Option[String] = Some("this is too good")

  val result = betterUnsafeMehod orElse betterSafeMethod
}
