package org.koinlabs


import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.annotation.JsonProperty
import org.koinlabs.util.toHex


class Block(var index: Int,
            var previousBlockHash: ByteArray,
            var timestamp: Long,
            var data: ByteArray,
            var nonce: Int) {

    @JsonProperty("hash") var hash: ByteArray = ByteArray(0)
//    @JsonProperty("hash") lateinit var hash: ByteArray

    @JsonGetter("hash")
    private fun getHashAsHash(): String {
        return this.hash.toHex()
    }

    @JsonGetter("data")
    private fun getDataAsString(): String {
        return this.data.toString(Charsets.UTF_8)
    }

    @JsonGetter("previousBlockHash")
    private fun getPreviousBlockHashAsHash(): String {
        return this.previousBlockHash.toHex()
    }

//    fun generateNextBlock(blockData: String): Block {
//        var previousBlock = getLatestBlock()
//        var nextIndex = previousBlock.index + 1
//        var nextTimestamp = Date()
//        var nextHash = calculateHash(nextIndex, previousBlock.hash, nextTimestamp, blockData)
//        return Block(nextIndex, previousBlock.hash, nextTimestamp, blockData, nextHash)
//    }

//    fun replaceChain(newBlockChain: Array<Block>) {
//        if ( newBlockChain.size > this.blockChain.size ) {
//           println("Received blockchain is valid. Replacing current blockchain with received blockchain")
//           this.blockChain = newBlockChain
//        }
//        else {
//            println("Received blockchain is invalid")
//        }
//    }

//    private fun calculateHash() {
//
//        val headers = this.previousBlockHash + this.data + this.timestamp.toByteArrayOfHexString()
//        this.hash = MessageDigest.getInstance("SHA-256").digest(headers)
//    }

    fun isValid(): Boolean {
       return ProofOfWork(this).isBlockValid()
    }

    override fun toString(): String {

        val mapper = jacksonObjectMapper()
        return mapper.writeValueAsString(this)

//        return Gson().toJson(this)
    }
}
