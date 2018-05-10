package br.com.ragneon.network.services

import io.reactivex.Single

interface UserServiceContract {
    fun getCredentials(): Single<String>
}