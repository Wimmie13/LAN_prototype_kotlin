package nl.asdproject2.java

fun main(args: Array<String>) {
    val client = GreetingClient("169.254.207.246", 6789)
    client.run()
}