package org.hsmak


object MethodsVsFunctions extends App {

  println("################################### Methods vs Functions ###################################")

  object MethOrFun {
    val f = (x: Int) => x + 1 // Function -> which is an object itself; that's why we can call apply() on it. so 'f' is a reference to the real object. Look up Method reference in Java8
    def g(x: Int) = x + 1 // Method -> it has to be part of an object so it's a member of class/object not a n object itself


  }

  println(MethOrFun.f.apply(4)) //a function aka an object too
  println(MethOrFun.f(4)) //a function aka an object too
  println(MethOrFun.g(4)) // a method not an object
  println

}
