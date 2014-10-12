package actor

import akka.actor.Actor
import akka.actor.Props

class DbWriterActor extends Actor{

  def receive = {
    
    case msg => println("discover")
    
  }
}