package nl.asdproject2.kotlin

import java.io.IOException

/**
 * Hello world!
 *
 */
object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val client = GreetingClient("localhost", 6789)
        client.run()
    }
}
