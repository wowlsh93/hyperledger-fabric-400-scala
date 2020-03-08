package fabric

class MSP {
  var pubKey: String = null
  var priKey: String = null
  var id: String = null


  def validating(_id: String): Boolean = id eq _id

}
