package org.koinlabs

import org.koinlabs.util.toByteArrayOfHexString
import org.koinlabs.util.toHex
import java.math.BigInteger
import java.security.MessageDigest

class ProofOfWork(private val block: Block) {

    private val TARGET_BITS = 20 // difficulty of mining

    private var target: BigInteger = BigInteger.ONE

    init {
        target = target.shiftLeft(256 - TARGET_BITS)
    }
    fun run(): Pair<Int, ByteArray> {
        var hashInt: BigInteger         // the integer representation of hash
        var hash = ByteArray(32)   // the hash itself
        var nonce = 0                   // the counter

//        println("Mining the block containing \"${block.data.toString(Charsets.UTF_8)}\"")

        val md = MessageDigest.getInstance("SHA-256");
        while (nonce < Long.MAX_VALUE) {
//            println("Nonce: " + nonce)
            val data = prepareData(nonce)
            val digest = md.digest(data)
//            println("Digest: " + digest.toHex())

            hashInt = BigInteger(digest)

            if ( hashInt.compareTo(BigInteger.ZERO) == 1 && hashInt.compareTo(target) == -1) {
                hash = digest
                break
            } else {
                nonce++
            }
        }

        return Pair(nonce, hash)
    }

    fun isBlockValid(): Boolean {
        val md = MessageDigest.getInstance("SHA-256");
        val hash = BigInteger(md.digest(prepareData(block.nonce)))
        return hash.compareTo(BigInteger.ZERO) ==1 && hash.compareTo(target) == -1
    }

    /**
     * @param nonce here is the counter from the Hashcash
     */
    private fun prepareData(nonce: Int): ByteArray {
        return block.previousBlockHash +
                block.data +
                block.timestamp.toByteArrayOfHexString() +
                TARGET_BITS.toLong().toByteArrayOfHexString() +
                nonce.toLong().toByteArrayOfHexString()
    }
}