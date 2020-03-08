package fabric

import akka.actor
import akka.actor.{Actor, Props}
import akka.event.Logging

object Committer {

  def props(msp : String) : Props =
    actor.Props(new Committer(msp))

}
class Committer (msp : String) extends Actor {
  val log = Logging(context.system, this)
  def receive = {
    case "hi" =>
      val parent = context.parent
      log.info(s"my parent $parent made me say hi!")
  }
  override def postStop() {
    log.info("child stopped!")
  }
}
