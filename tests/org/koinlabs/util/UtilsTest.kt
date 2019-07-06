package org.koinlabs.util

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.koinlabs.util.toHex
import java.util.*

internal class UtilsTest {


    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    @DisplayName("Convert ByteArray to hex")
    fun testToHex() {
        val input = "Hello World".toByteArray()
        assertEquals("48656c6c6f20576f726c64", input.toHex())
    }

    @Test
    @DisplayName("Convert Long to ByteArray")
    fun testToByteArrayOfHexString() {
        var input = 123456789L
        val b = byteArrayOf(55, 53, 98, 99, 100, 49, 53)
        assertArrayEquals(b, input.toByteArrayOfHexString())
    }

}