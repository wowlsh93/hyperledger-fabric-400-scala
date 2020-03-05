
package fabric

import akka.actor
import akka.actor.{Actor, ActorLogging, Props}

object Orderer {

  def props: Props =
    actor.Props(new Orderer())

}


class Orderer extends Actor with ActorLogging {

  def receive = {
    case  "create" =>
      context.actorOf(Props[Committer], "consumer")
      log.info(s"created a new child - children = ${context.children}")
  }
}