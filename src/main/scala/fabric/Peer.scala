package fabric

import akka.actor
import akka.actor.{Actor, ActorLogging, Props}

object Peer {
  def props: Props =
    actor.Props(new Peer())

}

class Peer extends Actor with ActorLogging{

  def receive = {
    case "create" =>
      context.actorOf(Props[Endorser], "endorser")
      context.actorOf(Props[Committer], "committer")
      log.info(s"created a new child - children = ${context.children}")

  }
}