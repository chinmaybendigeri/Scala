package part2.OOP

object LinkedList extends App {

  // 1. head => first element of a list
  // 2. tail => remainder of the list
  // 3. isEmptyList method to check if the list is empty
  // 4. addElement : Int => Add new element to a list
  // 5. Override toString method to print all the elements of a list

   abstract class List {
    def head : Int
    def tail : List
    def isEmptyList : Boolean
    def addElement(e : Int) : List
    def printElements : String
    override def toString: String = "[" + printElements +"]"
  }

  object EmptyList extends List{

    def head: Int = throw new NoSuchElementException

    def tail: List = throw new NoSuchElementException

    def isEmptyList: Boolean = {
      println("EmptyList Method")
      true
    }

    def addElement(e: Int): List = new NonEmptyList(e,EmptyList)

    def printElements: String = ""
  }

  class NonEmptyList(h : Int, t :List) extends List{

    def head: Int = {
      println(h)
      h
    }
    def tail: List = t

    def isEmptyList: Boolean = {
      println("NonEmptyList Method")
      false
    }

    def addElement(e: Int): List = new NonEmptyList(e,this)

    def printElements: String = {
      if (t.isEmptyList) "" + h
      else
        h + " " + t.printElements
    }
  }

  val mylist = new NonEmptyList(3,new NonEmptyList(4,new NonEmptyList(5,EmptyList)))
  println(mylist.head)
//  println(mylist.toString)
//
//  val mylist2 = new NonEmptyList(3,EmptyList)
//  println(mylist2.head)
//  println(mylist2.toString)
//
//  val mylistOfList = new NonEmptyList(13,EmptyList)
//  println(mylistOfList.addElement(25))
}
