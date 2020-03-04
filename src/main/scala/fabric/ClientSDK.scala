package fabric

import java.util
import java.util.ArrayList

object ClientSDK {

  def startFabric(): Unit ={
    Fabric.start();
  }

  def writeTrans(key : String, value : String): String = {
    val rwsets = Fabric.writeTransaction(key, value, Fabric.MSP_org1)

//    if (rwsets.getKey.msp == Fabric.MSP_peer1 && rwsets.getValue.msp == Fabric.MSP_peer2) {
//      val msps = new util.ArrayList[String]
//      msps.add(Fabric.MSP_peer1)
//      msps.add(Fabric.MSP_peer2)
//      val rwset = new RWSet("", key, value, msps)
//      Fabric.sendToOrderer(rwset)
//      return "ok"
//    }

    return "failed"
  }

  def getTrans(key : String): String = {

    val rwset = Fabric.readTranaction(key)
    return rwset
  }
}
