import org.hsmak.Complex

/**
  *
  * Link: https://www.youtube.com/watch?v=phjxbbLk4WM
  */

//################################### Singleton Objects ###################################

object MyObject {
  def foo(x: Int, y: Int) = x + y
}


class Employee(val firstName: String, val lastName: String, val title: String)

object Husain extends Employee("Huss", "AK", "Developer")

println(Husain.firstName)
println(Husain.lastName)
println(Husain.title)

// Diff between Classes & Objects: https://www.safaribooksonline.com/videos/learning-path-scala/9781491970850/9781491970850-video256895

val c = new Complex(1.2, 3.4)
println("imaginary part: " + c.im)
println(c)