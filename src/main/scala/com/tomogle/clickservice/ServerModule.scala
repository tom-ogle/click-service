package com.tomogle.clickservice

import java.net.InetSocketAddress

import org.apache.avro.ipc.specific.SpecificResponder
import org.apache.avro.ipc.{NettyServer, Server}

trait ServerModule {
  self: ServerConfigModule with ClickReportingProtocolModule =>

  val server: Server = startServer()

  def startServer(): Server

}
case class ServerConfig(port: Int)
trait ServerConfigModule {

  val serverConfig: ServerConfig

}

trait NettyServerModule extends ServerModule {
  self: ServerConfigModule with ClickReportingProtocolModule =>

  override def startServer(): Server = {
    val underlyingServer = new NettyServer(
      new SpecificResponder(ClickReportingProtocol.PROTOCOL, createClickReportingProtocol()),
      new InetSocketAddress(serverConfig.port)
    )
    sys addShutdownHook underlyingServer.close()
    underlyingServer.start()
    underlyingServer
  }
}
