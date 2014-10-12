package actor

import akka.actor.Actor
import akka.actor.Props
import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy._

import scala.concurrent.duration._




  case class Discover(network: String)
  
  class NodeDiscoveryActor(dbProps: Props) extends Actor {
    
  override def supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
  case _: ArithmeticException      =>  Resume
  case _: NullPointerException     => Restart
  case _: IllegalArgumentException => Stop
  case _: Exception                => Escalate
}
    
    def receive = {
      
      case Discover(network) => {
        
        println("Discovering nodes on network: " + network)
        
        sender ! "32A_jm20_5"
      }
      
    }
    
    
    
  }