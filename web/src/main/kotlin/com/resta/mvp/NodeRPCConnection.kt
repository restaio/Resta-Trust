package com.resta.mvp

import net.corda.client.rpc.CordaRPCClient
import net.corda.client.rpc.CordaRPCConnection
import net.corda.core.messaging.CordaRPCOps
import net.corda.core.utilities.NetworkHostAndPort
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

private const val CORDA_USER_NAME = "config.rpc.username"
private const val CORDA_USER_PASSWORD = "config.rpc.password"
private const val CORDA_NODE_HOST = "config.rpc.host"
private const val CORDA_RPC_PORT = "config.rpc.port"

@Component
open class NodeRPCConnection(
        @Value("\${$CORDA_NODE_HOST}") private val host: String,
        @Value("\${$CORDA_USER_NAME}") private val username: String,
        @Value("\${$CORDA_USER_PASSWORD}") private val password: String,
        @Value("\${$CORDA_RPC_PORT}") private val rpcPort: Int) {

//    val rpcConnection: CordaRPCConnection
//    lateinit var proxy: CordaRPCOps
//
//    @PostConstruct
//    fun initialiseNodeRPCConnection() {
//        val rpcAddress = NetworkHostAndPort(host, rpcPort)
//        val rpcClient = CordaRPCClient(rpcAddress)
//        val rpcConnection = rpcClient.start(username, password)
//        proxy = rpcConnection.proxy
//    }
//
//    @PreDestroy
//    override fun close() {
//        rpcConnection.notifyServerAndClose()
//    }

    val proxy: CordaRPCOps

    init {
        val rpcAddress =  NetworkHostAndPort(host,rpcPort)
        val rpcClient = CordaRPCClient(rpcAddress)
        val rpcConnection = rpcClient.start(username,password)
        proxy = rpcConnection.proxy
    }
}