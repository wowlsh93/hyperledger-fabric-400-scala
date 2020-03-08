package fabric

import akka.actor
import akka.actor.{Actor, Props}
import akka.event.Logging

object Endorser {

  def props(msp : String) : Props =
    actor.Props(new Endorser(msp))

}

class Endorser (endorser_msp : String) extends Actor {
  val log = Logging(context.system, this)
  def receive = {
    case trans: Transaction =>
      if (trans.peer_msp eq endorser_msp) {
        //
        //execute chain code !!!
        //
        val rwset = new RWSet(endorser_msp, trans.key, trans.value, null)

        sender ! rwset
      }
  }
  override def postStop() {
    log.info("child stopped!")
  }
}
