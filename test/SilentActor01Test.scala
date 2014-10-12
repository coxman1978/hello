import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Actor.Receive
import akka.actor.Props

import akka.testkit.TestKit
import akka.testkit.TestActorRef
import org.scalatest.WordSpecLike
import org.scalatest.MustMatchers



object SilentActorProtocol {
  case class SilentMessage(data: String)
  case class GetState(receiver: ActorRef)  
}

class SilentActor extends Actor {
  
  import SilentActorProtocol._
  
  var internalState = Vector[String]()
  
  def receive = {
    
    case SilentMessage(m) => 
      internalState = internalState :+ m 
      
    case GetState(sender) => sender ! internalState
  }
  
  def state = internalState
}


class SilentActor01Test extends TestKit(ActorSystem("testsystem"))
with WordSpecLike
with MustMatchers
with StopSystemAfterAll {
  
   import SilentActorProtocol._
   
  "A silent Actor" must {
    
    "Change state when it receives a message, single threaded" in {
      
      
      val silentActor = TestActorRef[SilentActor]
      
      silentActor ! SilentMessage("Plat aluminati")
      
      silentActor.underlyingActor.internalState must (contain("Plat aluminati"))
      
      
      
      //fail("not implemented yet")
    }
    
    "Change State when it receives a message, multithreaded" in {
      val silentActor = system.actorOf(Props[SilentActor],"s1")
      
      silentActor ! SilentMessage("Plat")
      silentActor ! SilentMessage("Numina")
      silentActor ! GetState(testActor)
      
      //expectMsg(Vector("Plat","Numina"))
      
      this.expectMsgPF() {
        
        case v: Vector[_] => (println("Vector internal state returned: " + v.head))
      }
     
    }
    
  }

}