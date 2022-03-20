package com.persAssistant.shopping_list.util

import android.util.Patterns

fun String.validateEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.validateName() = "\\D".toRegex().matches(this)




