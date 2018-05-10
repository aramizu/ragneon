package br.com.ragneon.database.realm.model

import br.com.ragneon.models.User
import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class UserSession : RealmObject() {

    @PrimaryKey
    @Required
    var token: String? = null

    private fun RealmObject.store() {
        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.insertOrUpdate(this)
            realm.commitTransaction()
        }
    }

    fun store(){
        (this as RealmObject).store()
    }

    fun toRealmObject(user: User): UserSession {
        this.token = user.token
        return this
    }
}