package nl.asdproject2.kotlin

import com.sun.xml.internal.ws.client.ClientTransportException

import java.io.DataInputStream
import java.io.IOException
import java.io.InputStream

class ClientInputThread(private val inFromServer: InputStream) : Thread() {

    override fun run() {
        while (true) {
            val `in` = DataInputStream(inFromServer)
            try {
                println(`in`.readUTF())
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }
}
