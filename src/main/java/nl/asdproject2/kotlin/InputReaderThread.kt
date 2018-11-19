package nl.asdproject2.kotlin

import java.io.*
import java.util.Observer

class InputReaderThread(private val serverIn: InputStream, private val serverOut: OutputStream) : Thread(), Observer {


    override fun run() {
        while (true) {
            val `in` = DataInputStream(serverIn)
            try {
                val message = `in`.readUTF()
                println(message)
                GreetingServer.update(message)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    override fun update(o: java.util.Observable, arg: Any) {
        val out = DataOutputStream(serverOut)
        try {
            out.writeUTF(arg.toString())
            out.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
