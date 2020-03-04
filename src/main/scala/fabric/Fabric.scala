package fabric

object Fabric {

  var MSP_org1 = "org1"

  def start(): Unit ={

    // 액터 시스템 생성 . 이름은 mysystem
    val ourSystem = ActorSystem("mysystem")

    val parent = ourSystem.actorOf(Props[ParentActor], "parent")
    parent ! "create"
    parent ! "create"
    Thread.sleep(1000)
    parent ! "hi"
    Thread.sleep(1000)
    parent ! "stop"
    Thread.sleep(1000)
    ourSystem.terminate()

  }

  def writeTransaction (key : String, value : String, msp : String): RWSet ={

    var rwset = new RWSet;

    return rwset;
  }

  def readTranaction (key : String) : String ={

    return "1";
  }
}


