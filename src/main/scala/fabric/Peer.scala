package fabric

import akka.actor
import akka.pattern.{ask, pipe}
import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps


class Peer (var context : ActorSystem) {
  def start() = {
    context.actorOf(Endorser.props("org1"), "endorser")
    context.actorOf(Committer.props("org1"), "committer")
  }

  def addTrans(trans : Transaction): RWSet = {
    val endorser = context.actorSelection("/user/endorser")

    implicit val timeout = Timeout(5 seconds)
    var future = endorser ? trans
    val result = Await.result(future, timeout.duration).asInstanceOf[RWSet]
    result
  }
}

