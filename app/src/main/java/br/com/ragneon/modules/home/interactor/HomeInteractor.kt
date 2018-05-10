package br.com.ragneon.modules.home.interactor

import br.com.ragneon.models.User
import br.com.ragneon.modules.home.contracts.HomeContracts
import br.com.ragneon.modules.home.datamanager.HomeRemoteDataManager
import io.reactivex.Single

class HomeInteractor(
        private val homeRemoteDataManager: HomeContracts.RemoteDataManager = HomeRemoteDataManager()
) : HomeContracts.Interactor {

    override fun getCredentials(): Single<User> {
        return homeRemoteDataManager.getCredentials()
    }
}
