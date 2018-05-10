package br.com.ragneon.utils

import android.content.Context
import br.com.ragneon.R
import java.io.IOException

object MockUtils {

    fun loadJSONFromAsset(context: Context, fileName: String): String? {
        val json: String?
        try {
            val input = context.assets.open(fileName)
            val size = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            json = String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }

    fun getMockProfileImage(clientId: Long): Int {
        return when (clientId.toInt()) {
            1 -> R.drawable.person1
            2 -> R.drawable.person2
            3 -> R.drawable.person3
            4 -> R.drawable.person4
            5 -> R.drawable.person5
            else -> 0
        }
    }

    fun getMockContactName(clientId: Long): String {
        return when (clientId.toInt()) {
            1 -> "Anderson Santos"
            2 -> "Bianca Gente Fina"
            3 -> "Débora Pomposa"
            4 -> "Darlene da Terra"
            5 -> "Fabiana Casca Grossa da Santa Maria de Lourdes"
            6 -> "Rafael Aramizu Gomes"
            else -> "Zé Ninguém"
        }
    }

    fun getMockContactPhoneNumber(clientId: Long): String {
        return when (clientId.toInt()) {
            1 -> "(11)95562-8945"
            2 -> "(11)97758-9652"
            3 -> "(11)97758-9652"
            4 -> "(17)98465-3245"
            5 -> "(16)98854-6425"
            6 -> "(11)97749-7071"
            else -> "(00)00000-0000"
        }
    }
}