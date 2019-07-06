package org.koinlabs

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import java.util.*

internal class BlockTest {

    lateinit var zeroBlock: Block
    val sometime = Date(1508547682000).time // Fri Oct 20 21:01:22 EDT 2017
    val aHash = "6ea2750bf098dee4b84d27583a1e8a446725856d145672cae636e659df"
    val blockData = "Random Block Data"

    @BeforeEach
    fun setUp() {
        zeroBlock = Block(0, aHash.toByteArray(), sometime, blockData.toByteArray(), 0)
    }

    @AfterEach
    fun tearDown() {
    }

//    @Test
//    @DisplayName("Calculate Block hash")
//    fun testCalculateHash() {
//        zeroBlock.calculateHash()
//        val h = "7c9905950551726eacda8d71f4f0eb0aaf30272afd9e762a4055df8d510339db"
//        assertEquals(h, zeroBlock.hash.toHex())
//    }

    @Test
    fun testIsValid() {
        assertFalse(zeroBlock.isValid())
    }

}