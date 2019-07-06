package org.koinlabs

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BlockchainTest {

    var chain = Blockchain()

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun testLatestBlockHeight() {
        val c = Blockchain()
        assertEquals(0, c.latestBlockHeight())
    }

    @Test
    fun testAddBlock() {
        val h = chain.latestBlockHeight()
        chain.addBlock("Hello Blockchain".toByteArray(Charsets.UTF_8))
        assertEquals(h+1, chain.latestBlockHeight())
        assertTrue(chain.getLatestBlock().isValid())
        assertEquals(chain.getLatestBlock(), chain.getBlock(h+1))
    }

}