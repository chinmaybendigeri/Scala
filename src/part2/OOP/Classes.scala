package part2.OOP

object Classes extends App{


  val person = new Person("Chinmay",26)
  println(person.name)
  println(person.age)
  println(person.height)
  person.getPerson()

  val person2 = new Person("Vivek",25)
  println(person2.name)
  println(person2.age)
  println(person2.height)
  person2.getPerson()


  class Person(val name : String, val age : Int = 10){

    val height : Double = 5.8

      def getPerson() : Unit =  {
      println("Inside Class: "+ this.name)
      println("inside Class: "+ this.age)
   }

  }


}


