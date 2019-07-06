package org.koinlabs

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.koinlabs.util.toHex
import java.util.*

internal class ProofOfWorkTest {

    lateinit var zeroBlock: Block
    val sometime = Date(1508547682000).time // Fri Oct 20 21:01:22 EDT 2017
    val aHash = "6EA2750BF098DEE4B84D27583A1E8A446725856D145672CAE636E659DF"
    val blockData = "Random Block Data"

    @BeforeEach
    fun setUp() {
        zeroBlock = Block(0, aHash.toByteArray(), sometime, blockData.toByteArray(), 0)
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    @DisplayName("Validate Block with PoW")
    fun testrun() {
        val pow = ProofOfWork(zeroBlock)
        val (nonce, hash) = pow.run()
        assertEquals(517558, nonce)
        assertEquals("000009a7c3301553e9565b975b40568b5cc6299558e262435bdd35de51644940", hash.toHex())
    }

    @Test
    fun testIsBlockValid() {
        val pow = ProofOfWork(zeroBlock)
        val (nonce, hash) = pow.run()
        zeroBlock.hash = hash
        zeroBlock.nonce = nonce
        assertTrue(ProofOfWork(zeroBlock).isBlockValid())
    }

}