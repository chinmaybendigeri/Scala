package part3.FunctionalProgramming

import scala.util.Random


object Options extends App{

  val aString : String = null
  //println(aString.length) // this line of code throws null pointer execption

  //there are 2 ways in which we can fix the Nullpointer Exception
  //1. Using Java Style throw catch in Scala

  try {
    println(aString.length)
  } catch {
    case e : NullPointerException => println("String is Null")
    case e : Exception => println("Generic Exception")
  }

  //2. Using Options which is provided in Scala
  // Instead of declaring the String as a String , declare it as a Option[String]
  val aOptionalInt : Option[Int] = Some(10)
  val aOptionalString : Option[String] = Some("Chinmay")
  val aOptionalNone : Option[String] = None

  val printString = aOptionalNone match {
    case Some(myName) => println(s"Hi, I am $myName")
    case None => println("I dont know who i am")
  }

  // Options can also be used to guard against Unsafe Methods/API's

  def unSafeMethod : String = null
  val result = Option(unSafeMethod)
  println(result)

  def unSafeMethodBetter : Option[String] = None
  def backUpMethod : Option[String] = Option("I remember my name now")
  val resultBetter = unSafeMethodBetter.orElse(backUpMethod)
  println(resultBetter)

  // We can use Options with default functions
  println(result.isEmpty)
 // println(result.get) // This will throw the NoSuchElement Found Exception as we are trying to get null

  // we can use Options with map,flatMap and filter as below
  println(aOptionalString.map(_ + " Bendigeri"))
  println(aOptionalInt.flatMap(x => Option(x * 2)))
  //println(aOptionalInt.filter(_ % 2 != 0))

  val config : Map[String,String] = Map(
    "host" -> "localhost",
    "port" -> "8080"
  )
  val invalidConfig : Map[String,String] = Map(
    "host" -> null,
    "port" -> null
  )

  class Connection(){
      def connect : String = "Connected"
  }

  object Connection{
    val r = new Random(System.nanoTime())
    def apply(hostname : String, port : String): Option[Connection] ={
        if(r.nextBoolean()) Some(new Connection())
        else None
    }
  }

//  val h = config.get("host")
//  val p = config.get("port")

  val h = invalidConfig.get("host")
  val p = invalidConfig.get("port")

  val connection = h.flatMap(h => p.flatMap(p => Connection.apply(h,p)))
  val connectionCase = connection match {
    case c: Option[Connection] => println("This is a valid Connection")
    case None => println("Invalid Connection")
    case _ => println("Ignored  Connection")
  }
  println("connection: "+connection)
  val connect = connection.map(c => c.connect)
  println(connect)
  connect.foreach(println)

}
