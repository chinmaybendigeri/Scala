package part2.OOP


object LinkedListGenerics extends App {

  // 1. head => first element of a list
  // 2. tail => remainder of the list
  // 3. isEmptyList method to check if the list is empty
  // 4. addElement : Int => Add new element to a list
  // 5. Override toString method to print all the elements of a list

  abstract class MyList[+A] {
    def head : A
    def tail : MyList[A]
    def isEmptyList : Boolean
    def addElement[B >: A](e : B) : MyList[B]
    def printElements : String
    override def toString: String = "[" + printElements +"]"
  }

  object EmptyList extends MyList[Nothing]{

    def head: Nothing = throw new NoSuchElementException

    def tail: MyList[Nothing] = throw new NoSuchElementException

    def isEmptyList: Boolean = {
      println("EmptyList Method")
      true
    }

    def addElement[B >: Nothing](e: B): MyList[B] = new NonEmptyList(e,EmptyList)

    def printElements: String = ""
  }

  class NonEmptyList[+A](h : A, t :MyList[A]) extends MyList[A]{

    def head: A = h

    def tail: MyList[A] = t

    def isEmptyList: Boolean = {
      println("NonEmptyList Method")
      false
    }

    def addElement[B >: A](e: B): MyList[B] = new NonEmptyList(e,this)

    def printElements: String = {
      if (t.isEmptyList) "" + h
      else
        h + " " + t.printElements
    }
  }
    val test = EmptyList
    val mylist : MyList[Int]= new NonEmptyList(3, EmptyList)
    println(mylist.head)
    println(mylist.toString)
    println(mylist.getClass)
    println(test.getClass)


//  val mylist2 = new NonEmptyList(3,EmptyList)
//  println(mylist2.head)
//  println(mylist2.toString)
//
//  val mylistOfList = new NonEmptyList(13,EmptyList)
//  println(mylistOfList.addElement(36))

//  val list = new NonEmptyList[Int](23,EmptyList)
//  println(list.addElement(67))

}

