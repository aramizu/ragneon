package br.com.ragneon.network.services

import br.com.ragneon.extensions.getUserApi
import br.com.ragneon.network.RetrofitClient
import io.reactivex.Single

class UserService : UserServiceContract {
    override fun getCredentials(): Single<String> {
        return RetrofitClient.getUserApi().getCredentials()
    }
}
