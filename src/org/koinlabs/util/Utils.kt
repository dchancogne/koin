package org.koinlabs.util

import com.google.gson.JsonSerializationContext
import com.google.gson.GsonBuilder


fun Long.toByteArrayOfHexString(): ByteArray = this.toString(16).toByteArray(Charsets.UTF_8)

fun ByteArray.toHex(): String {
    val s = StringBuffer()
    forEach { s.append(String.format("%02x",it)) }
    return s.toString()
}

//fun ByteArray.toString(): String = "Hash"
//
//
//GsonBuilder gson = new GsonBuilder();
//gson.registerTypeAdapter(ByteArray.class, new ByteArrayHashSerializer());
//
//private class ByteArrayHashSerializer implements JsonSerializer<ByteArray> {
//    public JsonElement serialize(ByteArray src, Type typeOf Src, JsonSerializationContext context) {
//        return new JsonPrimitive(src.toHex())
//    }
//}


