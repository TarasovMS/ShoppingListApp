package com.pers_assistant.shopping_list.util

import android.util.Patterns

fun String.validateEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.validateFields() = this.isNotEmpty()




