package com.chetan.moengageassignment.utils

import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.lang.Exception

class Helper {
    companion object {
        fun isValidEmail(email: String): Boolean {
            return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun hideKeyboard(view: View){
            try {
                val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }catch (e: Exception){

            }
        }

    }

    fun isValidEmail(email: String): Boolean {
        return true
    }

    fun isValidPassword(pwd: String): String = when{
        pwd.isBlank() -> {
            "Password is empty"
        }
        pwd.length < 6 -> {
            "length of password should be greater then 6"
        }
        pwd.length > 15 -> {
            "length of password should be less then 15"
        }
        else->{
            "Valid"
        }
    }
}