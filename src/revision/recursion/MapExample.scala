package revision.recursion

object MapExample extends App{
  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')

  println(numbers)
  println(chars)

  val newList = chars.flatMap(c => numbers.map(n => c+""+n))

  println(newList)

  //  syntactic sugar for scala
  val newSyntax = chars.flatMap {
    c =>
      numbers.filter {
        c => c % 2 == 0
      }.map {
        n => "" + c + n
      }
  }

  println(newSyntax)

}
