package com.zhihu.data

import com.zhihu.data.ip.IpInfo
import com.zhihu.data.Bar

case class Foo(name: String)

object Foo extends App {
  println(Foo("foo"))
  println(Bar("bar"))
}
