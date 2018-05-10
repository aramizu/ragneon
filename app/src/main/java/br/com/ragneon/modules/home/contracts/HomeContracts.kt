package br.com.ragneon.modules.home.contracts

import br.com.ragneon.commons.BaseContracts
import br.com.ragneon.models.User
import io.reactivex.Single

interface HomeContracts {

    interface RemoteDataManager {
        fun getCredentials() : Single<User>
    }

    interface View : BaseContracts.View {
        var presenter: Presenter
        fun showUserInformation(user: User)
    }

    interface Presenter {
        fun onGetCredentials()
        fun onSendMoneyClick()
        fun onHistoryClick()
    }

    interface Interactor {
        fun getCredentials(): Single<User>
    }

    interface Router {
        fun goToPaymentModule()
        fun goToHistoryModule()
    }
}

