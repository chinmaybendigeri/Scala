package revision

abstract class Animal {
  val name = "wild"

  def eat(): Unit = println("crunch crunch crunch!")
}

class Dog(override val name: String) extends Animal with Loyal { // traits are like implements in Java . we can have multiple traits for a single class
  override val isLoyal = true
  override def eat(): Unit = println("yum yum yum")
  def bark() : Unit = println("Ralf ralf ralf")
}

trait Loyal{
  val isLoyal : Boolean
}


object Inheritance extends App {
  val dog = new Dog("Lufy")
  dog.eat() // overriding the super class method with subclass method
  println(dog.isLoyal)

  val animal : Animal = new Dog("Ruby")
  animal.eat() // polymorphism -> this will actually call the most overridden method of an instance type
  //animal.bark()  // this will throw complie type error as animal type doesnt have bark method

  val new_animal = new Animal{
    override val name: String = "Godzilla"

    override def eat(): Unit = super.eat()
  }

  println(new_animal.getClass)

}

