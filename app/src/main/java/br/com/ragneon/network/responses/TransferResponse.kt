package br.com.ragneon.network.responses

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date

@JsonIgnoreProperties(ignoreUnknown = true)
class TransferResponse {

    @JsonProperty("Id")
    val Id: Long? = null

    @JsonProperty("ClienteId")
    val ClienteId: Long? = null

    @JsonProperty("Valor")
    val Valor: Double? = null

    @JsonProperty("Token")
    val Token: String? = null

    @JsonProperty("Data")
    val Data: Date? = null
}