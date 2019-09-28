package com.mogsev.testapplication.databinding.viewmodel

import android.text.TextUtils
import android.util.Patterns
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField

/**
 * Created by Eugene Sikaylo
 * email: mogsev@gmail.com
 */
class LoginActivityViewModel() : Observable.OnPropertyChangedCallback() {

    var login: ObservableField<String>? = null
        set(value) {
            field = value
            field?.addOnPropertyChangedCallback(this)
        }
    var checkedLogin: ObservableBoolean? = null
        set(value) {
            field = value
        }
    var password: ObservableField<String>? = null
        set(value) {
            field = value
            field?.addOnPropertyChangedCallback(this)
        }
    val loginEnabled: ObservableBoolean = ObservableBoolean(false)

    override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
        val email = login?.get()
        val pass = password?.get()
        val result = (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass))
                && (Patterns.EMAIL_ADDRESS.matcher(email as CharSequence).matches())
                && pass?.length!! >= 3

        loginEnabled.set(result)
        checkedLogin?.set(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email as CharSequence).matches())
    }

}
