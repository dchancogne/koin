package org.koinlabs

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.util.*

class Blockchain {

    @JsonProperty("blocks") private var blocks: ArrayList<Block> = ArrayList(0)

    init {
       blocks.add(newGenesisBlock())

    }

    private fun newBlock(data: ByteArray, previousBlockHash: ByteArray): Block {

        val block = Block(
                blocks.size,
                previousBlockHash,
                Date().time,
                data,
                0)
        val (nonce, hash) = ProofOfWork(block).run()
        block.hash = hash
        block.nonce = nonce

        return block

    }

    private fun newGenesisBlock(): Block {
        return newBlock("Genesis Block".toByteArray(Charsets.UTF_8), ByteArray(0))
    }

    fun addBlock(data: ByteArray) {
        val prevBlock = blocks.last()
        val newBlock = newBlock(data, prevBlock.hash)
        blocks.add(newBlock)
    }

    fun latestBlockHeight(): Int {
        return blocks.size-1
    }

    @JsonIgnore
    fun getLatestBlock(): Block {
        return blocks.last()
    }

    fun getBlock(height: Int): Block {
        return blocks.elementAt(height)
    }

    override fun toString(): String {
        val mapper = jacksonObjectMapper()
        return mapper.writeValueAsString(this)
    }

}
