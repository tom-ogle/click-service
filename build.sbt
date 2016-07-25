name := "avro-rpc-click-service"

version := "0.1"

scalaVersion := "2.11.8"

val avroVersion = "1.8.1"

sbtavrohugger.SbtAvrohugger.specificAvroSettings
(sourceDirectory in avroConfig) := new java.io.File("src/main/resources/avroschema")

libraryDependencies += "org.apache.avro" % "avro" % avroVersion
libraryDependencies += "org.apache.avro" % "avro-ipc" % avroVersion

