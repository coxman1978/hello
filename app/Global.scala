import akka.actor.{Actor, Props}
import akka.actor.ActorSystem

import play.api.Application
import play.api.libs.concurrent.Akka
import play.api.GlobalSettings
import play.api.templates.Html
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.ExecutionContext.Implicits.global



import actor._

object Global extends GlobalSettings{
  
  override def onStart(application: Application){
    
    println("App successfully started")
    
    val system = ActorSystem.create("commscripts")
    
    val nodeDiscoveryProps = Props[DbWriterActor]
    
    val nodeDiscoveryActor = system.actorOf(Props(new NodeDiscoveryActor(nodeDiscoveryProps)))
    
    
    nodeDiscoveryActor ! Discover("SFTI")
  }

}