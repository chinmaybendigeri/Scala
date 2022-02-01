package part2.OOP

object Example1 extends App{

  val writer1 = new Writer("Chinmay","Bendigeri",1996)
  println(writer1.getFullname())

  val novel1 = new Novel("The Pursuit of Happiness",2020,writer1)

  println(novel1.getAuthorAge())
  println(novel1.isWrittenBy())
  println(novel1.createCopy())



class Writer (firstName : String, lastName : String, val year : Int) {

  def getFullname (): String ={
    firstName +" "+ lastName
  }


}

class Novel(val name : String, val yearOfRelease : Int,val author : Writer) {

  def getAuthorAge(): Int = {
    yearOfRelease - author.year
  }

  def isWrittenBy(): String = {
    author.getFullname()
  }

  def createCopy(): String = {
    val novel = new Novel(name, 2025, author)
    novel.name + " " + novel.yearOfRelease + " " + novel.author.getFullname() + " " + novel.author.year
  }

}
}