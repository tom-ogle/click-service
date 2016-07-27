package com.tomogle.clickservice

trait ClickReportingProtocolModule {

  def createClickReportingProtocol(): ClickReportingProtocol

}

trait PrintlnClickReportingProtocolModule extends ClickReportingProtocolModule {
  override def createClickReportingProtocol(): ClickReportingProtocol = new PrintlnClickReportingProtocol
}
class PrintlnClickReportingProtocol extends ClickReportingProtocol {
  override def reportClick(click: Click): Null = {
    println(click)
    null
  }
}
