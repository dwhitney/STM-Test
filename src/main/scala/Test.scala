import se.scalablesolutions.akka.stm.Transaction.Global._
import se.scalablesolutions.akka.stm._

object Test{
    
    lazy val ref = TransactionalState.newRef[Int]
    
    def increment(): Unit = {
        implicit val tfn = "increment"
        atomic{
            val i = ref.get.getOrElse(0) + 1
            ref.swap(i)
        }
    }
    
    def total(): Int = {
        implicit val tfn = "total"
        atomic{
            ref.get.getOrElse(0)
        }
    }
    
    def main(args: Array[String]): Unit = {
        increment
        increment
        increment
        println(total)

    }
    
}