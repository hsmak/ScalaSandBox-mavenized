package org.hsmak


object Implicits extends App {


  println("------------ NoImplicit -------------")

  object NoImplicit {

    def operateOnString(str: String, op: String => Boolean) = {
      if (op(str))
        "It satisfies the passed operation!"
      else
        "It doesn't satisfy the passed operation!"
    }

    println(operateOnString("Hello", x => x.contains("H")))
    println(operateOnString("Hello", x => x.contains("q")))
  }

  NoImplicit
  println

  println("------------- WithImplicit ---------------")

  /**
    * Currying must be used if implicits are to be used
    */
  object WithImplicit {

    //implicit is to supply default value??
    implicit val op = (x: String) => x.contains("H")

    /**
      *
      * @param str
      * @param op : now this is a curried param
      * @return
      */
    def operateOnString(str: String)(implicit op: String => Boolean) = {
      if (op(str))
        "It satisfies the passed operation!"
      else
        "It doesn't satisfy the passed operation!"
    }

    println(operateOnString("Hello")) // implicit val is kicking in; which is: x.contains("H")
    println(operateOnString("Hello")(x => x.contains("H")))
    println(operateOnString("Hello")(x => x.contains("q")))

  }

  WithImplicit
  println

  println("------------------- AdvancedImplicits ---------------------")

  /**
    * Links:
    *     - https://www.safaribooksonline.com/videos/learning-path-scala/9781491970850/9781491970850-video256990
    */
  object AdvancedImplicitsWithVal {

    object Implicits {

      implicit val ctx_01: List[String => String] = List(
        _.trim,
        _.toLowerCase)

      //Same as previous one. just being explicit instead of inference
      implicit val ctx_02: List[String => String] = List(
        x => x.trim,
        x => x.toLowerCase)

      //More explicit than ctx_02
      implicit val ctx_03: List[String => String] = List(
        (x: String) => x.trim,
        (x: String) => x.toLowerCase)

    }

    def myImplicitParams(): Unit = {

      //curried method
      def _myFunction(str: String)(implicit ctx: List[String => String]): String = {

        ctx.foldLeft(str)((acc, func) => func(acc))

      }

      println("-- supply our concrete implementation instead of the implicit val")
      println(_myFunction("   FooOoO  ")(List(_.toLowerCase, _.trim)))
      println

      println("-- importing the implicit val ctx_01")
      import Implicits.ctx_01
      println(_myFunction("   FooOoO  "))

    }

    myImplicitParams()
  }

  AdvancedImplicitsWithVal
  println

  println("------------------- AdvancedImplicitsWithMethods ------------------------")

  object AdvancedImplicitsWithMethods {


    import java.net.URL

    object Implicits {

      implicit def stringToUrl(str: String): URL = new URL(str)

      implicit class StringToURLable(str: String) {
        def toURL: URL = new URL(str)
      }

    }

    /**
      *
      * @param url - of type URL. Instead of overloading the method to accept String, Implicit method will do the conversion from String => URL
      * @return
      */
    def printURL(url: URL): String = url.getHost

    println("-- regular call")
    println(printURL(new URL("http://www.google.com")))

    println("-- using implicit def")

    import Implicits.stringToUrl

    println(printURL("http://www.google.com"))

    println("-- using implicit class <- better and expressive")

    import Implicits.StringToURLable

    println(printURL("http://www.google.com".toURL)) // Notice the method (toURL) from the implicit class


  }

  AdvancedImplicitsWithMethods
  println

  println("------------------- AdvancedImplicitsExtra ------------------------")

  object AdvancedImplicitsExtra {


    import java.net.URL

    object Implicits {

      implicit def constructGoogleQueryURL(query: String): URL = new URL(s"http://www.google.com/?q=$query")

      implicit class QueryParamToURL(query: String){
        def toGoogleQueryURL : URL  = new URL(s"http://www.google.com/?q=$query")
      }

    }

    /**
      *
      * @param url - of type URL. Instead of overloading the method to accept String, Implicit method will do the conversion from String => URL
      * @return
      */
    def printURL(url: URL): String = url.toString

    println("-- using implicit def")
    import Implicits.constructGoogleQueryURL
    println(printURL("foo")) // will print: http://www.google.com/?q=foo


    println("-- using implicit class <- better and expressive")
    import Implicits.QueryParamToURL
    println(printURL("foo".toGoogleQueryURL)) // will print: http://www.google.com/?q=foo


  }

  AdvancedImplicitsExtra
  println
}