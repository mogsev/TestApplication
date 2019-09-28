package com.mogsev.testapplication.gui.activities.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mogsev.basemodule.gui.dialogs.ProgressDialogFragment
import com.mogsev.testapplication.R
import com.mogsev.testapplication.databinding.ActivityLoginBinding
import com.mogsev.testapplication.databinding.viewmodel.LoginActivityViewModel
import com.mogsev.testapplication.gui.activities.main.MainActivity

/**
 * Created by Eugene Sikaylo
 * email: mogsev@gmail.com
 */
class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBinding: ActivityLoginBinding
    private val mProgressDialog: ProgressDialogFragment by lazy(LazyThreadSafetyMode.NONE) {
        ProgressDialogFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mBinding.model = LoginActivityViewModel()
        mBinding.model?.login = mBinding.viewLogin?.getTextField()
        mBinding.model?.checkedLogin = mBinding.viewLogin?.getChecked()
        mBinding.model?.password = mBinding.viewPassword?.getTextField()
        mBinding.buttonLogin.setOnClickListener(this)
        mBinding.viewPassword.enablePassword()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_login -> onSignIn()
        }
    }

    private fun onSignIn() {
        startActivity(Intent(this, MainActivity::class.java))
    }

}
