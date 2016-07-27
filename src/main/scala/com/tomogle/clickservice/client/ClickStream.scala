package com.tomogle.clickservice.client

import com.tomogle.clickservice.Click

import scala.util.Random

trait ClickStream {
  def clickStream(): Stream[Click] = Stream.cons(generateClick(), clickStream())

  private lazy val ips = Vector("192.168.1.1", "192.168.1.2", "192.168.1.3", "192.168.1.4", "192.168.1.5")

  def generateClick(): Click = {
    val ip = ips(Random.nextInt(ips.length))
    val timestamp = Math.abs(Random.nextLong())
    Click(ip = ip, timestamp = timestamp, "http://www.mysite.com", "http://www.google.com", "")
  }
}
