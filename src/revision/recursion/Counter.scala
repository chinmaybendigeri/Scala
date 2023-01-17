package revision.recursion

class Counter(val count : Int = 1){

    println("Hi Bro")
    def inc = new Counter(count + 1)
    def this() = this(1)

    def print(name:String) = println(s"$name")
}

object Counter{

    println("Hello Brother")
    def main(args: Array[String]) = {
        val counter1 = new Counter()
        println(counter1.count)

        val test = counter1.inc
        println(test.count)

        counter1 print "vivek"

    }
}
