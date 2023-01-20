package revision

object CurriedFunctions extends App{


  def toCurry(f : (Int,Int) => Int) : Int => Int => Int  =
    x => y => f(x,y)

  val adder = toCurry((a,b) => a + b)

  println(adder(19)(10))

  def fromCurry(f : (Int => Int => Int)) : ( Int,Int) => Int =
    (x,y) => f(x)(y)

  val newAdder = fromCurry(a => b => a + b)

  println(newAdder(10,13))

  def compose(f : Int => Int ,g : Int => Int ) : Int => Int =
    x => f(g(x))

  def AndThen(f: Int => Int, g: Int => Int): Int => Int =
    x => g(f(x))

  val adderCompose = compose(_+4,_*5)
  println(adderCompose(5))


  val adderAndThen = AndThen(_ + 4, _ * 5)
  println(adderAndThen(5))

}
