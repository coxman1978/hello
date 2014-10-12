import org.scalatest.Suite
import org.scalatest.BeforeAndAfterAll
import akka.testkit.TestKit

trait StopSystemAfterAll extends BeforeAndAfterAll {

  this: TestKit with Suite =>
    override protected def afterAll(){
      
      super.afterAll()
      println("Shutting down!!!!")
      
      system.shutdown()
    }
}