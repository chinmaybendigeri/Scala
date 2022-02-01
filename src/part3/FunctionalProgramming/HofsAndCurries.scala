package part3.FunctionalProgramming

object HofsAndCurries extends App {

  // 1. head => first element of a list
  // 2. tail => remainder of the list
  // 3. isEmptyList method to check if the list is empty
  // 4. addElement : Int => Add new element to a list
  // 5. Override toString method to print all the elements of a list


  //  trait MyPredicate[-T] {
  //    def test(elem: T): Boolean
  //  }
  //
  //  trait MyTransformer[-A, B] {
  //    def transform(elem: A): B
  //  }

  abstract class MyList[+A] {
    def head: A

    def tail: MyList[A]

    def isEmptyList: Boolean

    def addElement[B >: A](e: B): MyList[B]

    def printElements: String

    override def toString: String = "[" + printElements + "]"

    // (1,2,3).map(n * 2) => (2,4,6)
    def map[B](transformer: A => B): MyList[B]

    def foreach(f: A => Unit): Unit

    def filter(predicate: A => Boolean): MyList[A]

    def flatMap[B](transformer: A => MyList[B]) :MyList[B]

    def concat[B >: A](list : MyList[B]) : MyList[B]

    def sort(compare: (A,A) => Int) : MyList[A]

    def zipWith[B, C](list: MyList[B],f: (A,B) => C) : MyList[C]

    def fold[B](start: B)(operator : (B,A) => B) :B
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

    def map[B](transformer: Nothing => B): MyList[B] = EmptyList

    def filter(predicate: Nothing => Boolean): MyList[Nothing] = EmptyList

    def flatMap[B](transformer: Nothing => MyList[B]) :MyList[B] = EmptyList

    def concat[B >: Nothing](list : MyList[B]) : MyList[B] = list

    def foreach(f: Nothing => Unit): Unit = ()

    def sort(compare: (Nothing, Nothing) => Int) = EmptyList


    def zipWith[B, C](list: MyList[B], f: (Nothing, B) => C): MyList[C] =
      if (!list.isEmptyList)  throw new RuntimeException("list do not have the same length")
      else EmptyList

    override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
  }

 case class NonEmptyList[+A](val h: A, t: MyList[A]) extends MyList[A] {

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

    def map[B](transformer: A => B): MyList[B] =
      new NonEmptyList(transformer.apply(h), t.map(transformer))

    def filter(predicate: A => Boolean ): MyList[A] =
      if (predicate.apply(h)) new NonEmptyList(h, t.filter(predicate))
      else
        t.filter(predicate)

    def concat[B >: A](list : MyList[B]) : MyList[B] = {
      println(s"concat():  List [$h,${t.toString} with ${list.toString}]")
      new NonEmptyList(h, t.concat(list))
    }

    def flatMap[B](transformer: A => MyList[B])  = {
      println(s"Called Transformer with Head: $h")
      println(s"Now the tail is : ${t.toString}")
      transformer.apply(h) concat (t.flatMap(transformer))
    }

    def foreach(f: A => Unit): Unit = {
      f(h)
      t.foreach(f)
    }

    def sort(compare: (A, A) => Int): MyList[A] = {
     def insert(x : A, sortedList : MyList[A]) : MyList[A] = {
       if (sortedList.isEmptyList) {
         println("if: " + sortedList.printElements + " x = " +x)
         new NonEmptyList(x, EmptyList)
       }
       else if (compare(x, sortedList.head) <= 0 ) // 2-1 = 1
      {
        println("else if: " + sortedList.printElements + " x = " +x)
        new NonEmptyList(x, sortedList)
      }
       else {
         println("else: " + sortedList.printElements + " x = " +x)
         new NonEmptyList(sortedList.head, insert(x, sortedList.tail))
       }
     }
      println(t)
      // [1,2,3]
      // h = 3 , sortedList = EmptyList
      // h = 2, sortedList = [3,EmptyList]  return 1  = else part =>  [3,[2,EmptyList]]
      // h = 1, sortedList = [3,[2,EmptyList]]
      val sortedList =  t.sort(compare)
      println("sortedList before Inserting "+ sortedList.printElements)
      insert(h,sortedList)
    }
    // [1,2,3]
    // [3,4,5]


   def zipWith[B, C](list: MyList[B],f: (A,B) => C) : MyList[C] = {
     if (list.isEmptyList)  throw new RuntimeException("list do not have the same length")
     else
       new NonEmptyList(f(h,list.head),t.zipWith(list.tail,f))
   }
    //(1,2,3).fold(2)((x,y) => x*y)
   //(2,3).fold(2)((x,y) => x*y)
   //(3).fold(4)((x,y) => x*y)
   //().fold(12)((x,y) => x*y)
   //12
    def fold[B](start: B)(operator: (B, A) => B): B = t.fold(operator(start,h))(operator)
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
    val mylist1: MyList[Int] = new NonEmptyList(1, new NonEmptyList(2, new NonEmptyList(3,EmptyList)))
    val mylist2: MyList[Int] = new NonEmptyList(4, new NonEmptyList(5, new NonEmptyList(6,EmptyList)))

//    val mylist1: MyList[Int] = new NonEmptyList(5, EmptyList)
//    val mylist2: MyList[Int] = new NonEmptyList(4, EmptyList)

   // println(mylist1.sort((x,y) => y - x))

   // val mylist2 = EmptyList
   // mylist2.sort((x,y) => y - x)
    println(mylist1.zipWith(mylist2,(x : Int, y:Int) => x * y))

    println(mylist1.fold(2)((x,y) => x*y))

//    println(mylist1.zipWith(mylist2,new ((Int, Int) => Int) {
//      override def apply(x: Int, y: Int): Int = x * y
//    }))
//

//    val mylist2: MyList[Int] = new NonEmptyList(68, new NonEmptyList(15, EmptyList))
//
//    println(mylist2.filter(x => if (x % 2 == 0) true else false))
//
//    val mylist3: MyList[Int] = new NonEmptyList(1, new NonEmptyList(2, EmptyList))
//
//
//    println(mylist3.flatMap(x => new NonEmptyList(x, new NonEmptyList(x+1,EmptyList))))

  }

  PredicateTransformRunner
}





