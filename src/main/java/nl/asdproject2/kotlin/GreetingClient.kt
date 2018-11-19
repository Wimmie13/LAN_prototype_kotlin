package nl.asdproject2.kotlin

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.Socket

class GreetingClient(private val serverName: String, private val port: Int) {
    private var client: Socket? = null
    private var username: String? = null

    private val theUsername: String
        @Throws(IOException::class)
        get() {
            val reader = BufferedReader(InputStreamReader(System.`in`))
            print("Enter your username: ")
            return reader.readLine()
        }

    init {

        try {
            this.username = theUsername
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun run() {
        try {
            Writer.write("Connecting to $serverName on port $port")
            client = Socket(serverName, port)
            Writer.write("Just connected to " + client!!.remoteSocketAddress)

            ClientInputThread(client!!.getInputStream()).start()

            startMessaging()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun startMessaging() {
        var message = ""
        while (message.toLowerCase() != "close") {
            try {
                message = input()
                Writer.writeUTF(client!!.getOutputStream(), username + ": " + message)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        close()
    }

    private fun close() {
        try {
            client!!.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    @Throws(IOException::class)
    private fun input(): String {
        val reader = BufferedReader(InputStreamReader(System.`in`))
        return reader.readLine()
    }
}