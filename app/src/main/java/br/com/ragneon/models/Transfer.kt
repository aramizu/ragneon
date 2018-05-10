package br.com.ragneon.models

import br.com.ragneon.network.responses.TransferResponse
import java.util.Date

class Transfer(transferResponse: TransferResponse = TransferResponse()) {
    var Id = transferResponse.Id ?: 0
    var ClienteId = transferResponse.ClienteId ?: 0
    var Valor = transferResponse.Valor ?: 0.toDouble()
    var Token = transferResponse.Token ?: String()
    var Data = transferResponse.Data ?: Date()

    constructor(clientId: Long, value: Double) : this() {
        this.ClienteId = clientId
        this.Valor = value
    }
}