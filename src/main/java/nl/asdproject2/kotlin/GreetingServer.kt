package nl.asdproject2.kotlin

import java.net.*
import java.io.*
import java.util.ArrayList
import java.util.Observable
import java.util.Observer

class GreetingServer @Throws(IOException::class)
constructor(port: Int) : Observable() {
    private val serverSocket: ServerSocket

    init {
        serverSocket = ServerSocket(port)
        clients = ArrayList()
        greetingServer = this
        run()
    }

    fun run() {
        while (true) {
            try {
                val server = serverSocket.accept()
                println("Just connected to " + server.remoteSocketAddress)

                val client = InputReaderThread(server.getInputStream(), server.getOutputStream())
                clients.add(client)
                client.start()
            } catch (e: IOException) {
                e.printStackTrace()
                break
            }

        }
    }

    companion object {
        private lateinit var clients: MutableList<Observer>
        private lateinit var greetingServer: GreetingServer

        @JvmStatic
        fun main(args: Array<String>) {
            val port = 6789
            try {
                GreetingServer(port)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }

        fun update(message: String) {
            for (client in clients) {
                client.update(greetingServer, message)
            }
        }
    }
}