package com.packt.akka.restApi.models

import spray.json.DefaultJsonProtocol

case class Tweet(author: String, body: String)

object TweetProtocol extends DefaultJsonProtocol {
  implicit val TweetFormat = jsonFormat2(Tweet.apply)
}
