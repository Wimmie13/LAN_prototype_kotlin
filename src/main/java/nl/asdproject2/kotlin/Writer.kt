package nl.asdproject2.kotlin

import java.io.*

object Writer {
    fun write(output: String) {
        println(output)
    }

    @Throws(IOException::class)
    fun writeUTF(outToServer: OutputStream, output: String) {
        val out = DataOutputStream(outToServer)
        out.writeUTF(output)
        out.flush()
    }

    @Throws(IOException::class)
    fun writeIn(inputStream: InputStream) {
        val `in` = DataInputStream(inputStream)
        println(`in`.readUTF())
    }
}
