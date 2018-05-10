package br.com.ragneon.network.requests

data class SendMoneyRequest(
        val ClienteId: Long,
        var token: String,
        val valor: Double
)