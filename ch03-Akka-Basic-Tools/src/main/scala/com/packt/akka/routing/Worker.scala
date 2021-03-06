package com.packt.akka.routing

import akka.actor.Actor

class Worker extends Actor {
  import Worker._

  def receive = {
    case msg: Work =>
      println(s"I received Work Message and My ActorRef: ${self}")
  }
}

object Worker {
  case class Work() 
}