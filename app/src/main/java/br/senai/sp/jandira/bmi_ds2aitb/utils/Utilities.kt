package br.senai.sp.jandira.bmi_ds2aitb.utils

import java.util.Locale

fun convertNumberToLocale(value: Double): String{
    return String.format(Locale.getDefault(), "%.2f", value)
}