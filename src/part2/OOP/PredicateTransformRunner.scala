package part2.OOP

object PredicateTransformRunner extends App {

  // 1. head => first element of a list
  // 2. tail => remainder of the list
  // 3. isEmptyList method to check if the list is empty
  // 4. addElement : Int => Add new element to a list
  // 5. Override toString method to print all the elements of a list


  trait MyPredicate[-T] {
    def test(elem: T): Boolean
  }

  trait MyTransformer[-A, B] {
    def transform(elem: A): B
  }

  abstract class MyList[+A] {
    def head: A

    def tail: MyList[A]

    def isEmptyList: Boolean

    def addElement[B >: A](e: B): MyList[B]

    def printElements: String

    override def toString: String = "[" + printElements + "]"

    // (1,2,3).map(n * 2) => (2,4,6)
    def map[B](transformer: MyTransformer[A, B]): MyList[B]

    def filter(predicate: MyPredicate[A]): MyList[A]

    def flatMap[B](transformer: MyTransformer[A, MyList[B]]) :MyList[B]

    def concat[B >: A](list : MyList[B]) : MyList[B]
  }

  object EmptyList extends MyList[Nothing] {

    def head: Nothing = throw new NoSuchElementException

    def tail: MyList[Nothing] = throw new NoSuchElementException

    def isEmptyList: Boolean = {
      //println("EmptyList Method")
      true
    }

    def addElement[B >: Nothing](e: B): MyList[B] = new NonEmptyList(e, EmptyList)

    def printElements: String = ""

    def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = EmptyList

    def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = EmptyList

    def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]) :MyList[B] = EmptyList

    def concat[B >: Nothing](list : MyList[B]) : MyList[B] = list
  }

  class NonEmptyList[+A](val h: A, t: MyList[A]) extends MyList[A] {

    def head: A = h

    def tail: MyList[A] = t

    def isEmptyList: Boolean = {
      //println("NonEmptyList Method")
      false
    }

    def addElement[B >: A](e: B): MyList[B] = new NonEmptyList(e, this)

    def printElements: String = {
      if (t.isEmptyList) "" + h
      else
        h + " " + t.printElements
    }

    def map[B](transformer: MyTransformer[A, B]): MyList[B] =
      new NonEmptyList(transformer.transform(h), t.map(transformer))

    def filter(predicate: MyPredicate[A]): MyList[A] =
      if (predicate.test(h)) new NonEmptyList(h, t.filter(predicate))
      else
        t.filter(predicate)

    def concat[B >: A](list : MyList[B]) : MyList[B] = {
      println(s"concat():  List [$h,${t.toString} with ${list.toString}]")
      new NonEmptyList(h, t.concat(list))
    }

    def flatMap[B](transformer: MyTransformer[A, MyList[B]]) :MyList[B] = {
      println(s"Called Transformer with Head: $h")
      println(s"Now the tail is : ${t.toString}")
      transformer.transform(h) concat (t.flatMap(transformer))
    }

  }

  //  val test = EmptyList
  //  val mylist : MyList[Int]= new NonEmptyList(3, EmptyList)
  //  println(mylist.head)
  //  println(mylist.toString)
  //  println(mylist.getClass)
  //  println(test.getClass)


  //  val mylist2 = new NonEmptyList(3,EmptyList)
  //  println(mylist2.head)
  //  println(mylist2.toString)
  //
  //  val mylistOfList = new NonEmptyList(13,EmptyList)
  //  println(mylistOfList.addElement(36))

  //  val list = new NonEmptyList[Int](23,EmptyList)
  //  println(list.addElement(67))

  object PredicateTransformRunner {
    val mylist1: MyList[Int] = new NonEmptyList(10, new NonEmptyList(25, EmptyList))

    println(mylist1.map(new MyTransformer[Int, Int] {
      override def transform(elem: Int): Int = elem * 2
    }))

    val mylist2: MyList[Int] = new NonEmptyList(68, new NonEmptyList(15, EmptyList))

    println(mylist2.filter(new MyPredicate[Int] {
      override def test(elem: Int): Boolean = if (elem % 2 == 0) true else false
    }))

     val mylist3: MyList[Int] = new NonEmptyList(1, new NonEmptyList(2, EmptyList))


    println(mylist3.flatMap(new MyTransformer[Int, MyList[Int]] {
       override def transform(elem: Int): MyList[ Int] = {
         println(s"transform($elem): Returning List with [$elem ${elem+1}]")
         new NonEmptyList(elem, new NonEmptyList(elem+1,EmptyList))
       }
    }))

  }

  PredicateTransformRunner
}



