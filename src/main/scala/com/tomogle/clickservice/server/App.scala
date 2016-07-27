package com.tomogle.clickservice.server

import scala.util.Try

object App {

  val defaultPort = 65111

  def main(args: Array[String]): Unit = {
    val portNumber = Try(args(0).toInt).getOrElse(defaultPort)
    val serverConfig = ServerConfig(port = portNumber)
    val clickServiceApp = ProductionClickService(config = serverConfig)
    clickServiceApp.server.join()
  }

}
