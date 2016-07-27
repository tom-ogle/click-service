package com.tomogle.clickservice.server

import com.tomogle.clickservice.{Click, ClickReportingProtocol}

trait ClickReportingProtocolModule {

  def createClickReportingProtocol(): ClickReportingProtocol

}

trait PrintlnClickReportingProtocolModule extends ClickReportingProtocolModule {
  override def createClickReportingProtocol(): ClickReportingProtocol = new PrintlnClickReportingProtocol
}
class PrintlnClickReportingProtocol extends ClickReportingProtocol {
  override def reportClick(click: Click): Null = {
    println(s"Received click: $click")
    null
  }
}
