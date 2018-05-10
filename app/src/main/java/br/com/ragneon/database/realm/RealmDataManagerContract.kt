package br.com.ragneon.database.realm

import br.com.ragneon.database.realm.model.UserSession
import br.com.ragneon.models.User

interface RealmDataManagerContract {
    fun saveUserSession(user: User)
    fun retrieveUserSession(): UserSession
}
