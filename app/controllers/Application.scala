package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.pattern._
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

import actor._
import com.theice.commscripts.SshGetStatActor
import java.util.Date

import models.Node

object Application extends Controller {

  lazy val actorSystem = ActorSystem.create("commscripts")
  implicit val timeout = Timeout(5 seconds)

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  
  def dashboard = Action {
    
    Ok(views.html.dashboard("ICECheckout"))
  }

  def plats = Action {
    val names = List("Dextra", "Killa", "Platinum")
    Ok(views.html.plats(names))
  }
  
  def testImplicit(node: Node) = Action {
    //this.NotImplemented
    Ok(views.html.nodedetail(node))
  }

  def jsonTest = Action {

    Ok(Json.toJson(List("Platinum")))
  }

  def discover = Action.async {

    val actor = actorSystem.actorOf(Props[NodeDiscoveryActor])

    val futureResult = actor ? Discover("Remote-CPE")

    futureResult.mapTo[String] map { result =>
      Ok(views.html.plats(List(result)))
    }

  }

  def connect = Action.async {

    val getStatActor = actorSystem.actorOf(Props(new SshGetStatActor("localhost", "dwright","melvina@home1")))

    val futureResult = getStatActor ? "connect"

    futureResult.mapTo[String] map { result =>
      Ok(views.html.plats(List(result)))
    }

  }

}