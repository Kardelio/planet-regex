package bk.edu

fun main() {
    println("Hello World!")
    val t = """.*""".toRegex()
    val c = "hello"
    val out = t.matches(c)
    println(c)
}