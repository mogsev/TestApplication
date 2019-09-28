package com.mogsev.basemodule.databinding.viewmodels

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField

/**
 * Created by Eugene Sikaylo
 * email: mogsev@gmail.com
 */

class BorderedEditTextViewModel() {

    val textField: ObservableField<String> = ObservableField("")
    val isChecked: ObservableBoolean = ObservableBoolean(false)

}
