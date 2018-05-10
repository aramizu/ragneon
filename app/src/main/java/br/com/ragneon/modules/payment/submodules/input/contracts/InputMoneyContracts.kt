package br.com.ragneon.modules.payment.submodules.input.contracts

import io.reactivex.Completable

interface InputMoneyContracts {

    interface RemoteDataManager {
        fun sendMoney(clientId: Long, money: Double): Completable
    }

    interface View {
        var presenter: Presenter
    }

    interface Presenter {
        fun onSendMoneyClick(clientId: Long, money: Double)
        fun onCreate()
    }

    interface Interactor {
        fun sendMoney(clientId: Long, money: Double): Completable
    }
}

