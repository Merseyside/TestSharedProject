package com.merseyside.testsharedproject

import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI

class ClientWebSocket(url: String) : WebSocketClient(URI(url)) {
    override fun onOpen(handshakedata: ServerHandshake?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMessage(message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(ex: Exception?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}