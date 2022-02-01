package part3.FunctionalProgramming

import scala.util.control.Breaks.{break, breakable}

object CollectionsForLoopsBreaks extends App{

  val listOfIntegers = List(1,2,3,4)
  val listOfChars = List('a','b','c','d')

  //usign flatMap and Map methods
  val combinations = listOfIntegers.flatMap(i => listOfChars.map(c => ""+c+i))
  println(combinations)
  //using for - yield to get the combinations
  val combinationsFor = for {
    i <- listOfIntegers
    c <- listOfChars
  } yield ("" + c + i)

  println(combinationsFor)

  //normal for loops in scala with collections but doesnt return the List
  val combinationsPrint = for(x <- listOfIntegers; y <-listOfChars)
  {
    print(""+y+x+" ");
  }

  println("\n")
  // for loops in scala with default datatypes
  // until and to
  //using breaks with for
  breakable {
    for(x <- 0 to 10)
    {
      if(x==5) {
        println("Is Breaking")
        break
      }
      else
        print(x);
    }
  }

  println("\n")
  for(x <- 0 until 10)
  {
    print(" "+x);
  }

}
