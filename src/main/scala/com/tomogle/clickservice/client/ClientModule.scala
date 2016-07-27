package com.tomogle.clickservice.client

import java.net.InetSocketAddress

import com.tomogle.clickservice.{Click, ClickReportingProtocol}
import org.apache.avro.ipc.NettyTransceiver
import org.apache.avro.ipc.specific.SpecificRequestor
import org.apache.avro.specific.SpecificData

trait ClientModule {

  val client: ClickClient

  trait ClickClient {
    def reportclick(click: Click): Unit
    def reportclicks(clicks: Click*): Unit
    def close(): Unit
  }

}

trait NettyClientModule extends ClientModule {

  val port: Int

  override val client: ClickClient = NettyClickClient(port)

  case class NettyClickClient(port: Int) extends ClickClient {

    private val underlyingNettyClient = new NettyTransceiver(new InetSocketAddress(port))

    private val clickProtocolClient: ClickReportingProtocol = SpecificRequestor.getClient(classOf[ClickReportingProtocol],
      new SpecificRequestor(
        ClickReportingProtocol.PROTOCOL,
        underlyingNettyClient,
        SpecificData.get))

    def reportclick(click: Click): Unit = clickProtocolClient.reportClick(click)
    def reportclicks(clicks: Click*): Unit = clicks foreach clickProtocolClient.reportClick

    def close(awaitCompletion: Boolean = false): Unit = underlyingNettyClient.close(awaitCompletion)
    override def close(): Unit = underlyingNettyClient.close()

  }

}
