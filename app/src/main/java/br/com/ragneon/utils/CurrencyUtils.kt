package br.com.ragneon.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

object CurrencyUtils {

    private const val CURRENCY_FULL_FORMAT = "'R$ '###,###,###,###,##0.00"
    private const val CURRENCY_FORMAT = "###,###,###,###,##0.00"

    fun format(value: String): String {
        val trimValue = value.replace("R", "")
                .replace("$", "")
                .replace("R$", "")
                .replace(",", "")
                .replace(".", "")
                .replace(" ", "")

        val doubleValue: Double = if (trimValue.isNotEmpty()) {
            trimValue.toDouble() / 100
        } else {
            0.toDouble()
        }

        val decimalFormat = getDecimalFormat(CURRENCY_FULL_FORMAT)
        return decimalFormat.format(doubleValue)
    }

    fun format(value: Double, noCurrencySymbol: Boolean = false): String {
        val pattern = if (noCurrencySymbol) CURRENCY_FORMAT else CURRENCY_FULL_FORMAT
        val decimalFormat = DecimalFormat(pattern)
        return decimalFormat.format(value)
    }

    fun getDouble(text: String): Double {
        return if (text.isNotEmpty()) {
            text.replace("R", "")
                    .replace("$", "")
                    .replace(",", "")
                    .replace(".", "")
                    .replace(" ", "").toDouble() / 100
        } else {
            0.toDouble()
        }
    }

    private fun getDecimalFormat(pattern: String): DecimalFormat {
        val decimalFormat = DecimalFormat(pattern)
        val decimalFormatSymbols = DecimalFormatSymbols()

        /** Define o caractere separador das casas decimais.  */
        decimalFormatSymbols.decimalSeparator = ','
        /** Define o caractere separador dos grupos das milhares.  */
        decimalFormatSymbols.groupingSeparator = '.'
        /** Seta o formatador de simbolos ao formatador do decimal.  */
        decimalFormat.decimalFormatSymbols = decimalFormatSymbols

        return decimalFormat
    }
}