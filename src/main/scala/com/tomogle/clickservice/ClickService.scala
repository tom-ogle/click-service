package com.tomogle.clickservice

trait ClickService {
  self: ServerModule =>

}

trait ProductionClickService extends ClickService
  with NettyServerModule with PrintlnClickReportingProtocolModule {

  config: ServerConfigModule =>
}

object ProductionClickService {
  def apply(config: ServerConfig): ProductionClickService = new ProductionClickService() with ServerConfigModule {
    override lazy val serverConfig: ServerConfig = config
  }
}
