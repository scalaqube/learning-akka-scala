package com.packt.akka.restApi

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.stream.{ActorMaterializer, Materializer}
import com.packt.akka.restApi.db.TweetManager
import com.packt.akka.restApi.models.{Tweet, TweetEntity}
import spray.json._

import scala.concurrent.ExecutionContext

trait RestApi {


    implicit val system: ActorSystem

    implicit val materializer: Materializer

    implicit val ec: ExecutionContext

    val route =
        pathPrefix("tweets") {
            (post & entity(as[Tweet])) { tweet =>
                complete {
                    TweetManager.save(tweet) map { r =>
                        Created -> Map("id" -> r.id).toJson
                    }
                }
            } ~
                (get & path(Segment)) { id =>
                    complete {
                        TweetManager.findById(id) map { t =>
                            OK -> t toJson
                        }
                    }
                } ~

                (delete & path(Segment)) { id =>
                    complete {
                        TweetManager.deleteById(id) map { _ =>
                            NoContent
                        }
                    }
                } ~
                (get) {
                    complete {
                        TweetManager.find.map { ts =>
                            OK -> ts.map(i => i.as[TweetEntity]).toJson
                        }
                    }
                }
        }

}

object Api extends App with RestApi {

    override implicit val system = ActorSystem("rest-api")

    override implicit val materializer = ActorMaterializer()

    override implicit val ec = system.dispatcher

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    Console.readLine()

    bindingFuture
        .flatMap(_.unbind())
        .onComplete(_ => system.terminate())

}