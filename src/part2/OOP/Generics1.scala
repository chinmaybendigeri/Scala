package part2.OOP

object Generics1 extends App {

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  class CovariantList[+A] {
    def setAnimal(name: String ,limbs : Int): Unit ={
      println(name + " " + limbs)

    }
  }

  val covariantList: CovariantList[Animal] = new CovariantList[Cat]
  //covariantList = new CovariantList[Dog]
  //covariantList.setAnimal("Cat",4)

  class InvariantList[A]
  val invariantList : InvariantList[Animal] = new InvariantList[Animal]

  class ContravariantList[-A]
  val contravarList : ContravariantList[Cat] = new ContravariantList[Animal]


}
