package actor

import akka.actor.Actor

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter


case class UpdateNodeInfo(nodeInfo: String)


class NoneInfoFileWriterActor(node: String, nodeDir: File) extends Actor{
  
  def receive = {
    
    case UpdateNodeInfo(nodeInfo) => println("Updating Node info: " + nodeInfo + " for node "+ node )
    
  }

}