import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}

import scala.collection.immutable
import scala.util.Random

object Stream extends App {
  // implicit val actorSystem = ActorSystem()
  // import actorSystem.dispatcher
  // implicit val flowMaterializer = ActorMaterializer()

  // val input = Source(1 to 100)

  // val normalize = Flow[Int].map(_ * 2)

  // val output = Sink.foreach[Int](println)

  // input.via(normalize).runWith(output).andThen {
  //   case _ =>
  //     actorSystem.shutdown()
  //     actorSystem.awaitTermination()
  // }
}
