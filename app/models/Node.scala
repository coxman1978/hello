package models

import play.api.mvc.PathBindable
import play.api.data.format.Formatter
import play.api.data.FormError
import play.api.data.format.Formats

import scala.util.control.Exception.allCatch

case class Node(id: Long, name: String, ip: String)

object Node {

  var nodes = Set(
    Node(1,"111N-P1-CNR01","10.60.1.1"),
    Node(2,"111N-P2-CNR01","10.60.1.2"),
    Node(3,"118-P1-CNR01","10.60.1.3")
  )
  
  def findAll = nodes.toList.sortBy(_.name)
  
  def findById(id: Long) = nodes.find(_.id == id)
  
  def addNode(node: Node) = {
    nodes = nodes + node
    }
  
  
  implicit def longToNode(id: Long): Node = nodes.filter(_.id == id).head
  
  implicit def articlePathBindable(implicit longBinder: PathBindable[Long]) = new PathBindable[Node] {
 
  def bind(key: String, value: String): Either[String, Node] =
    for {
      id <- longBinder.bind(key, value).right
      article <- Node.findById(id).toRight("Node not found").right
    } yield article
 
  def unbind(key: String, node: Node): String =
    longBinder.unbind(key, node.id)
 
}

/*    implicit def nodeFormat: Formatter[Node] = new Formatter[Node] {
    def bind(key: String, data: Map[String, String]) = {
                    
            nodes.find(_.id == 1).toRight(Seq(FormError(key, "error.required", Nil)))
            
    }
    
    def unbind(key: String, value: Node) = Map(key -> value.name)
  }
  */
  
}