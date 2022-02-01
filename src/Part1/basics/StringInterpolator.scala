package Part1.basics

object StringInterpolator extends App {

  val name: String = "Chinmay"
  val n: Float = 2.0f

  // S Interpolator
  val str: String = s"My name is $name and i am learning Scala from past $n days"
  println(str)

  // F Interpolator
  println(f"My name is $name%s and i am leaning Scala from past $n%2.5f days")

  // Raw Interpolator

  println(raw"This is a new \n line")
  println("This is a new \n line")

}
