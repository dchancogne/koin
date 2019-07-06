package org.koinlabs

import io.javalin.Javalin
// import io.javalin.embeddedserver.jetty.websocket.WsSession
import io.javalin.websocket.WsSession
import mu.KotlinLogging
import org.koinlabs.util.toHex

//import java.util.ArrayList


private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {

    val app = Javalin.create().port(7000)
    val peer = Javalin.create().port(7001)
    val blockchain = Blockchain()
    var peers: ArrayList<WsSession> = ArrayList(0)

    app.enableCaseSensitiveUrls()

    app.get("/") { ctx ->
        logger.info { "/" }
        ctx.result("Koin Node. Up and running.")
    }

    app.get("/latestBlockHeight") { ctx ->
        logger.info { "/latestBlockHeight" }
        ctx.result(blockchain.latestBlockHeight().toString())
    }

    app.get("/latestBlock") { ctx ->
        logger.info { "/latestBlock" }
        val b = blockchain.getLatestBlock()
        logger.debug { "Block index=${b.index}; Hash=${b.hash.toHex()}"}
        ctx.result(b.toString())
    }

    app.get("/blocks") { ctx ->
        logger.info { "/blocks" }
        logger.debug { "Blockchain length: ${blockchain.latestBlockHeight()}" }
        ctx.result(blockchain.toString())
    }

    app.post("/addBlock") { ctx ->
       logger.info { "/addBlock" }
       blockchain.addBlock(ctx.body().toByteArray(Charsets.UTF_8))
       ctx.result("ok")
    }

    app.get("/peers") { ctx ->
        logger.info { "/peers"}
        ctx.result(peers.map { "${it.remoteAddress.address.hostAddress}:${it.remoteAddress.port}" }.toString())
    }

    peer.ws("/") { ws ->
        ws.onConnect { session ->
            logger.info { "WebSocket connection: ${session.remoteAddress.address.hostAddress}:${session.remoteAddress.port}" }
            peers.add(session)
            session.remote.sendString("OK")
        }
        ws.onClose { session, statusCode, reason ->
            logger.info { "WebSocket disconnect: ${session.remoteAddress.address.hostAddress}:${session.remoteAddress.port}" }
            peers.remove(session)
        }
        ws.onError { session, throwable ->
            logger.info { "WebSocket error: ${session.remoteAddress.address.hostAddress}:${session.remoteAddress.port}" }
            peers.remove(session)
        }
    }

    logger.info { "Starting REST server"}
    app.start()
    logger.info { "Starting P2P server"}
    peer.start()
}

