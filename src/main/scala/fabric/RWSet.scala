package fabric

import scala.collection.mutable

class RWSet {

  var msp: String = null
  var key: String = null
  var value: String = null
  var peers_msp: mutable.ArrayBuffer[String] = null
}
