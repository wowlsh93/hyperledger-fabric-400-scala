package fabric

import java.security.{MessageDigest, NoSuchAlgorithmException}
import java.util
import java.util.concurrent.ConcurrentHashMap

import scala.collection.mutable

class Ledger {
  var stateDB = new LevelDB
  var blockchain = new util.ArrayList[_Block]

  def createGenesisBlock(): Unit = {
    val genesisBlock = new _Block
    genesisBlock.index = 0
    genesisBlock.timestamp = 0
    val genesis = new util.ArrayList[_Transaction]
    genesis.add(new _Transaction("genesis_key", "3 organization"))
    genesisBlock.trans = genesis
    genesisBlock.hash = calculateHash(genesisBlock)
    genesisBlock.prevHash = ""
    blockchain.add(genesisBlock)
  }

  def addBlock(block: Block): Unit = {
    val prevBlock = blockchain.get(blockchain.size - 1)
    val newBlock = generateBlock(prevBlock, block)
    blockchain.add(newBlock)
  }

  def generateBlock(oldBlock: _Block, block: Block): _Block = {
    val newBlock = new _Block
    newBlock.index = oldBlock.index + 1
    newBlock.timestamp = System.currentTimeMillis / 1000
    newBlock.prevHash = oldBlock.hash
    newBlock.trans = block.trans
    newBlock.hash = calculateHash(newBlock)
    newBlock
  }

  def setState(trans: _Transaction): Unit = {
    stateDB.setValue(trans.key, trans.value)
  }

  def getState(trans: Transaction): String = stateDB.getValue(trans.key)

  def calculateHash(block: _Block): String = {
    val trans_concated = new StringBuilder
    block.trans.forEach((t: _Transaction) => trans_concated.append(t))

    //string record = hama::string_format("%d%lf%s%s\n", block->Index, block->Timestamp, trans_concated, block->PrevHash);
    val record = "afefwfwef2323f2332f23f23wfwefweref23423423sdvsvsr423423432432432f23f23f23432234322f"
    //    var md = null
    //    try md = MessageDigest.getInstance("SHA-256")
    //    catch {
    //      case e: NoSuchAlgorithmException =>
    //        e.printStackTrace()
    //    }
    //    md.update(record.getBytes)
    //    val hashedmsg = md.digest
    //    val builder = new StringBuilder
    //    for (b <- hashedmsg) {
    //      builder.append(String.format("%02x", b))
    //    }
    //    builder.toString
    //  }

    record
  }
}

class _Block {
  var index = 0
  var timestamp = 0L
  var hash = ""
  var prevHash = ""
  var trans : util.ArrayList[_Transaction] =  null

  def this(_index: Int, _timestamp: Long, _hash: String, _prevHash: String, _trans: util.ArrayList[_Transaction]) {
    this()
    this.index = _index
    this.timestamp = _timestamp
    this.hash = _hash
    this.prevHash = _prevHash
    this.trans = _trans
  }
}

class Block{
  var endorsers = new util.ArrayList[String]
  var trans = new util.ArrayList[_Transaction]
}

class LevelDB {
  var db = new ConcurrentHashMap[String, String]
  def getValue(key: String): String = db.get(key)
  def setValue(key: String, value: String): Unit = {
    db.put(key, value)
  }
}

