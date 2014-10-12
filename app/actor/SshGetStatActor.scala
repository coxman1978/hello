package com.theice.commscripts

import akka.actor.Actor
import akka.actor.PoisonPill

import com.decodified.scalassh._

class SshGetStatActor(host: String, username: String, password: String) extends Actor {

  def receive = {
    case "connect" => {

    val hostConfig = HostConfig(PasswordLogin(username, password), "localhost", enableCompression = true)
    
      println("connecting to localhost")
    
      SSH("localhost",hostConfig) { client =>
        println("Successfully connected to localhost")
        client.exec("pwd; cal; date").right.map { result =>
          println("Result:\n" + result.stdOutAsString())
          
          sender ! result.stdOutAsString()
        }
        
        client.close
      }

       self ! PoisonPill
    
    }

  }

}