package br.com.ragneon.models

import br.com.ragneon.R

data class User(
        val token: String,
        val userName: String = "Rafael Aramizu Gomes",
        val email: String = "rafael.aramizu@gmail.com",
        val profileImage: Int = R.drawable.perfil
)