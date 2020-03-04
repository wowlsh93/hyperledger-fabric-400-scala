import fabric.ClientSDK

import scala.util.control.Breaks

object MainSystem extends App {
  println("....................relaySystem start !!....................")

  println("Start 1")

  ClientSDK.startFabric()

  println("Start 2")

  val startTime = System.currentTimeMillis


  for (i <- 0 until 1) {
    ClientSDK.writeTrans(String.valueOf(i), String.valueOf(i))
  }

  System.out.println("Start 3")
  var loop = new Breaks
  loop.breakable {
    while (true) {
      val result = ClientSDK.getTrans("1")
      if (result != null && !(result == ""))
        loop.break
    }
  }
  System.out.println("Start 4")

  val estimatedTime = System.currentTimeMillis - startTime
  System.out.println("took " + estimatedTime + " ms")


  try Thread.sleep(1000 * 100)
  catch {
    case e: InterruptedException =>
      e.printStackTrace()
  }
}
