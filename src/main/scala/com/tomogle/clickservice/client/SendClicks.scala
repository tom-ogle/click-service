package com.tomogle.clickservice.client

import java.util.concurrent.TimeUnit

import scala.util.Try

object SendClicks {

  val defaultPort = 65111

  def main(args: Array[String]): Unit = {
    val portNumber = Try(args(0).toInt).getOrElse(defaultPort)
    val clickSenderModule = ClickSenderModule(portNumber)
    clickSenderModule.sendClicks(2000L)
    sys addShutdownHook clickSenderModule.client.close()
  }
}

case class ClickSenderModule(port: Int) extends NettyClientModule with ClickStream {

  def sendClicks(pauseTimeBetweenClicksMs: Long): Unit = {
    val click = clickStream().head
    println(s"Sending click: $click")
    client.reportclick(click)
    TimeUnit.MILLISECONDS.sleep(pauseTimeBetweenClicksMs)
    sendClicks(pauseTimeBetweenClicksMs)
  }
}
