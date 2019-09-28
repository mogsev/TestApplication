package com.mogsev.testapplication.databinding.viewmodel

import androidx.databinding.ObservableBoolean

/**
 * Created by Eugene Sikaylo
 * email: mogsev@gmail.com
 */

class MainSoundPackageViewModel() {

    val isActivate: ObservableBoolean = ObservableBoolean(false)
    val isDeleteEnabled: ObservableBoolean = ObservableBoolean(false)

}
