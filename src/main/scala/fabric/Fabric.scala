package fabric

import akka.actor.{ActorRef, ActorSystem, Props}

object Fabric {

  var MSP_org1 = "org1"

  val fabric = ActorSystem("fabric");

  val peerActor = fabric.actorOf(Peer.props, "Peer")
  val ordererActor = fabric.actorOf(Orderer.props, "Orderer")

  def start(): Unit ={


  }

  def writeTransaction (key : String, value : String, msp : String): RWSet ={

    var rwset = new RWSet;

    return rwset;
  }

  def readTranaction (key : String) : String ={

    return "1";
  }
}


