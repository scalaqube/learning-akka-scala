package com.packt.akka.persistence

import akka.persistence._
import akka.actor.{ Actor, ActorRef, ActorSystem, Props }


object Persistent extends App {
  import Counter._

  val system = ActorSystem("persistent-actors")

  val counter = system.actorOf(Props[Counter],"counter")

  Thread.sleep(10000)

  counter ! Cmd(Increment(3))

  counter ! Cmd(Increment(5))

  counter ! Cmd(Increment(5))
  counter ! Cmd(Increment(5))
  counter ! Cmd(Increment(5))
  counter ! Cmd(Increment(5))

  counter ! Cmd(Decrement(3))

  counter ! "print"

  Thread.sleep(10000)

  system.terminate()

}






