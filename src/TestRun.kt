import java.util.*

import org.koinlabs.util.*

//fun Long.toByteArrayOfHexString(): ByteArray = this.toString(16).toByteArray(Charsets.UTF_8)
fun Long.toStringMine(): String = this.toString(16)

fun main(args: Array<String>) {
    println("Result:")
//    println(1L.toStringMine())
//    println(15L.toStringMine())
//    println(Arrays.toString(1L.toByteArrayOfHexString()))
//    println(Arrays.toString(15L.toByteArrayOfHexString()))
//    println(Arrays.toString(123121L.toByteArrayOfHexString()))
    println(Date())
    println(Date().time)
    println(Date().time.toString(16))
    println(Arrays.toString(Date().time.toByteArrayOfHexString()))
    println(Date().time.toByteArrayOfHexString().toString(Charsets.UTF_8))
    println(Date().time.toByteArrayOfHexString().toHex())
    println(java.lang.Long.toBinaryString(Date().time))
    println(Date().time.toByte())

//    println(Arrays.toString("Hello".toByteArray(Charsets.UTF_8)))
//    println(Arrays.toString("World".toByteArray(Charsets.UTF_8)))
//    println(Arrays.toString("Hello".toByteArray(Charsets.UTF_8) + "World".toByteArray(Charsets.UTF_8)))
}