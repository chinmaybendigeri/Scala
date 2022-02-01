package Part1.basics

object CBVvsCBN extends App{

  def callbyValue(x : Long): Unit ={
    println("callbyValue x: " + x )
    println("callbyValue x: " + x )
  }

  callbyValue(System.nanoTime())

  def callbyName(x : => Long): Unit ={
    println("callbyName x: " + x )
    println("callbyName x: " + x )
  }

  callbyName(System.nanoTime())

  def defautArgs(x : Int,y : Int): Unit ={

  }

}
