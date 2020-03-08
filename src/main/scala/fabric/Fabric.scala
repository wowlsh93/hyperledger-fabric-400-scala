package fabric

import akka.actor.{ActorRef, ActorSystem, Props}

object Fabric {

  var MSP_org1 = "org1"
  val fabric = ActorSystem("fabric");
  var peer = new Peer(fabric)

  def start(): Unit = {
    peer.start()
  }

  def writeTransaction (key : String, value : String, msp : String): RWSet ={

    val t = new Transaction(msp, key, value)
    val rwset1 = peer.addTrans(t)
    return rwset1
  }

  def readTranaction (key : String) : String ={
    return "1";
  }
}


