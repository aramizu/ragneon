package br.com.ragneon.extensions

import br.com.ragneon.network.RetrofitClient
import br.com.ragneon.network.api.BankApi
import br.com.ragneon.network.api.UserApi

fun RetrofitClient.Companion.getUserApi(): UserApi {
    return RetrofitClient.getInstance()
            .create(UserApi::class.java)
}

fun RetrofitClient.Companion.getBankApi(): BankApi {
    return RetrofitClient.getInstance()
            .create(BankApi::class.java)
}