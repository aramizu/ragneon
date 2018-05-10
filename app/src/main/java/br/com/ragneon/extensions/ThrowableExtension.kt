package br.com.ragneon.extensions

import br.com.ragneon.errors.BaseThrowableError
import br.com.ragneon.network.constants.NetworkErrorConstants
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import retrofit2.HttpException
import retrofit2.Response


fun Throwable.getErrorMessage(): String {
    var errorMessage: String? = null

    if (this is HttpException) {
        errorMessage = if (this.code() == NetworkErrorConstants.INTERNAL_ERROR) {
            "Ocorreu um erro interno. Por favor, tente novamente"
        } else {
            parseError(this.code(), this.response())
        }
    } else if (this is BaseThrowableError) {
        errorMessage = this.errorMessage
    }

    return errorMessage ?: "Ocorreu um erro"
}

fun Throwable.getErrorCode(): Int {
    var errorCode = NetworkErrorConstants.NO_DATA

    if (this is HttpException) {
        errorCode = this.code()
    }
    return errorCode
}

fun Throwable.isUnauthorized(): Boolean {
    val httpException = this as? HttpException
    if (httpException != null) {
        return httpException.code() == NetworkErrorConstants.UNAUTHORIZED
    }
    return false
}

private fun parseError(responseCode: Int, response: Response<*>) : String? {
    val jsonError = response.errorBody()?.string()
    val errors: JsonArray
    var message: String? = null

    if (responseCode == NetworkErrorConstants.NOT_FOUND) {
        message = "Dados não encontrados"
    } else if (!jsonError.isNullOrEmpty()) {
        errors = JsonParser().parse(jsonError) as JsonArray
        message = ""
        for (error in errors) {
            message += error.asString
        }
    }

    return message
}