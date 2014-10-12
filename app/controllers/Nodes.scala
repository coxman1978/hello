package controllers

import play.api.data.Form
import play.api.data.Forms.{mapping, longNumber, nonEmptyText}
import play.api.i18n.Messages
import play.api._
import play.api.mvc._


object Nodes extends Controller {
  
  import models.Node
  
  private val nodeForm: Form[Node] = Form(
      mapping(
          "id" -> longNumber,
          "name" -> nonEmptyText,
          "ip" -> nonEmptyText
          
      )(Node.apply)(Node.unapply))
  
  def list = Action { implicit request =>
  	//val nodes = List("111N-P1-CNR01","350C-P2-CNR02","LON-P2-CNR01","LON-P5-CSX01")
    val nodes = Node.findAll
 	Ok(views.html.nodes.list(nodes))
  }
  
  def addNode = Action { implicit request =>
    
    val newNodeForm = nodeForm.bindFromRequest()
    
    newNodeForm.fold(
    hasErrors = { form => 
      Ok(views.html.nodes.list(Node.findAll.drop(1)))
    }, 
    success = {newProduct => 
      Ok(views.html.nodes.list(Node.findAll.take(2)))
    })
    
  }

}
