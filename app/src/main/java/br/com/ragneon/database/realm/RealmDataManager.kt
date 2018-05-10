package br.com.ragneon.database.realm

import br.com.ragneon.database.realm.model.UserSession
import br.com.ragneon.models.User
import io.realm.Realm

class RealmDataManager : RealmDataManagerContract {

    override fun saveUserSession(user: User) {
        UserSession().toRealmObject(user).store()
    }

    override fun retrieveUserSession(): UserSession {
        val userSession = Realm.getDefaultInstance().where(UserSession::class.java).findFirst()
        return userSession ?: UserSession()
    }
}