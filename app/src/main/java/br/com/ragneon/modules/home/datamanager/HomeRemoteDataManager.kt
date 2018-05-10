package br.com.ragneon.modules.home.datamanager

import br.com.ragneon.database.realm.RealmDataManager
import br.com.ragneon.database.realm.RealmDataManagerContract
import br.com.ragneon.models.User
import br.com.ragneon.modules.home.contracts.HomeContracts
import br.com.ragneon.network.services.UserService
import br.com.ragneon.network.services.UserServiceContract
import io.reactivex.Single

class HomeRemoteDataManager(
        private val userService: UserServiceContract = UserService(),
        private val realmDataManager: RealmDataManagerContract = RealmDataManager()
) : HomeContracts.RemoteDataManager {

    override fun getCredentials() : Single<User> {
        /*
        return userService.getCredentials()
                .map {
                    val user = User(it)
                    realmDataManager.saveUserSession(user)
                    return@map user
                }
                */
        val user = User("867e424c-8c5f-4459-ba8a-da811770fd70")
        realmDataManager.saveUserSession(user)
        return Single.just(user)
    }
}