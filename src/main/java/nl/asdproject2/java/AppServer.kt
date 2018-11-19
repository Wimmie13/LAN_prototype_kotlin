package nl.asdproject2.java

import java.io.IOException

fun main(args: Array<String>) {
    val port = 6789
    try {
        GreetingServer(port)
    } catch (e: IOException) {
        e.printStackTrace()
    }
}