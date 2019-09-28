package com.mogsev.basemodule.gui.views

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import androidx.annotation.ColorInt
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.mogsev.basemodule.R
import com.mogsev.basemodule.databinding.ViewBorderedEditTextBinding
import com.mogsev.basemodule.databinding.viewmodels.BorderedEditTextViewModel
import java.lang.ref.WeakReference

/**
 * Created by Eugene Sikaylo
 * email: mogsev@gmail.com
 */
class BorderedEditTextView : ConstraintLayout {

    private lateinit var mBinding: ViewBorderedEditTextBinding

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(attrs)
    }

    private fun initView(attrs: AttributeSet?) {
        // inflate view
        val layoutInflater = WeakReference(LayoutInflater.from(context)).get()
        mBinding = ViewBorderedEditTextBinding.inflate(layoutInflater!!, this, true)
        mBinding.model = BorderedEditTextViewModel()

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BorderedEditTextView)
        try {
            setTitle(typedArray.getString(R.styleable.BorderedEditTextView_title))
            setHint(typedArray.getString(R.styleable.BorderedEditTextView_hint))
            val color = ContextCompat.getColor(context, R.color.colorPrimary)
            setTitleBackground(
                typedArray.getColor(
                    R.styleable.BorderedEditTextView_title_background,
                    color
                )
            )
            setImeOptions(
                typedArray.getInteger(
                    R.styleable.BorderedEditTextView_ime_option,
                    EditorInfo.IME_ACTION_DONE
                )
            )
        } finally {
            typedArray.recycle()
        }

        // preview mode on Android Studio
        if (isInEditMode) {
            return
        }
    }

    fun setTitle(title: String?) {
        mBinding.textViewTitle.text = title
    }

    fun setHint(hint: String?) {
        mBinding.editText.setHint(hint)
    }

    fun setTitleBackground(@ColorInt color: Int) {
        mBinding.textViewTitle.setBackgroundColor(color)
    }

    fun enablePassword() {
        mBinding.editText.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    }

    fun getTextField() = mBinding.model?.textField

    fun getChecked() = mBinding.model?.isChecked

    fun setImeOptions(option: Int) {
        mBinding.editText.imeOptions = option
    }

}
