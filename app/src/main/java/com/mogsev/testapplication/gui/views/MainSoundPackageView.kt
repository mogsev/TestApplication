package com.mogsev.testapplication.gui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import com.mogsev.testapplication.R
import com.mogsev.testapplication.databinding.ViewMainSoundPackageBinding
import com.mogsev.testapplication.databinding.viewmodel.MainSoundPackageViewModel
import com.mogsev.testapplication.gui.views.listener.OnSwipeTouchListener
import timber.log.Timber
import java.lang.ref.WeakReference

/**
 * Created by Eugene Sikaylo
 * email: mogsev@gmail.com
 */

class MainSoundPackageView : CardView, View.OnClickListener {

    private lateinit var mBinding: ViewMainSoundPackageBinding
    private var mSwiping: Boolean = false

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(attrs)
    }

    private fun initView(attrs: AttributeSet?) {
        // inflate view
        val layoutInflater = WeakReference(LayoutInflater.from(context)).get()
        mBinding = ViewMainSoundPackageBinding.inflate(layoutInflater!!, this, true)
        mBinding.model = MainSoundPackageViewModel()
        // preview mode on Android Studio
        if (isInEditMode) {
            return
        }

        initSwipe()
    }

    override fun onClick(v: View?) {
        Timber.i("onClick")
        when (v?.id) {
            R.id.layout_delete -> onDeleteInstalledPreset()
        }
    }

    private fun onClickMainLayout() {
        Timber.i("onClickMainLayout")
        mBinding.model?.isActivate?.get()?.let {
            when (it) {
                true -> onDeactivateCommand()
                false -> onActivateCommand()
            }
        }
    }

    private fun onActivateCommand() {
        Timber.i("onActivateCommand")
        onActivate()
    }

    private fun onDeactivateCommand() {
        Timber.i("onDeactivateCommand")
        onDeactivate()
    }

    fun onActivate() {
        mBinding?.model?.isActivate?.set(true)
        postOnAnimation {
            mBinding.imageViewCover.animate().scaleX(SCALE_MAX).scaleY(SCALE_MAX).setDuration(
                ANIMATION_DURATION
            ).start()
        }
        mBinding.viewCoverRedGradient.setBackgroundResource(R.drawable.bg_shape_sound_card_gradient_red_vertical)
        mBinding.textViewSoundName.alpha = 1.0f
    }

    fun onActivateWithoutAnimation() {
        mBinding?.model?.isActivate?.set(true)
        mBinding.viewCoverRedGradient.setBackgroundResource(R.drawable.bg_shape_sound_card_gradient_red_vertical)
        mBinding.textViewSoundName.alpha = 1.0f
        mBinding.imageViewCover.scaleX = SCALE_MAX
        mBinding.imageViewCover.scaleY = SCALE_MAX
    }

    fun onDeactivate() {
        Timber.i("onDeactivate")
        mBinding?.model?.isActivate?.set(false)
        postOnAnimation {
            mBinding.imageViewCover.animate().scaleX(SCALE_MIN).scaleY(SCALE_MIN).setDuration(
                ANIMATION_DURATION
            ).start()
        }
        mBinding.textViewSoundName.alpha = 0.5f
    }

    private fun cleanSwipe() {
        Timber.i("cleanSwipe")
        if (mBinding?.model?.isDeleteEnabled!!.get()) {
            onSwipeToRightAnimation()
        }
    }

    fun onSwipeToLeftAnimation() {
        Timber.i("onSwipeToLeftAnimation")
        if (!mBinding?.model?.isDeleteEnabled!!.get() && !mSwiping) {
            mSwiping = true
            val marginLeft = resources.getDimensionPixelSize(R.dimen.margin_dp_8)
            val pos = mBinding.mainLayout.width + marginLeft
            mBinding.layoutDelete.x = pos.toFloat()
            Timber.i("New positionX: %s", pos)
            mBinding.layoutDelete.visibility = View.VISIBLE
            val offsetX = resources.getDimensionPixelOffset(R.dimen.width_dp_120)
            val coverX = mBinding.layoutCover.x
            val deleteX = mBinding.layoutDelete.x
            mBinding.layoutDelete.animate().x(deleteX - offsetX)
                .setDuration(ANIMATION_DURATION).start()
            mBinding.layoutCover.animate().x(coverX - offsetX)
                .setDuration(ANIMATION_DURATION).start()
            mBinding?.model?.isDeleteEnabled?.set(true)
            postDelayed({ mSwiping = false }, ANIMATION_DURATION)
        }
    }

    fun onSwipeToRightAnimation() {
        Timber.i("onSwipeToRightAnimation")
        if (mBinding?.model?.isDeleteEnabled!!.get() && !mSwiping) {
            mSwiping = true
            val offsetX = resources.getDimensionPixelOffset(R.dimen.width_dp_120)
            val coverX = mBinding.layoutCover.x
            val deleteX = mBinding.layoutDelete.x
            mBinding.layoutDelete.animate().x(deleteX + offsetX)
                .setDuration(ANIMATION_DURATION).start()
            mBinding.layoutCover.animate().x(coverX + offsetX)
                .setDuration(ANIMATION_DURATION).start()
            mBinding?.model?.isDeleteEnabled?.set(false)
            postDelayed({ mSwiping = false }, ANIMATION_DURATION)
        }
    }

    private fun initSwipe() {
        Timber.i("initSwipe")
        setOnTouchListener(OnSwipeListener(context))
        mBinding.layoutDelete.setOnClickListener(this)
    }

    inner class OnSwipeListener(context: Context) : OnSwipeTouchListener(context) {
        override fun onSwipeRight() {
            Timber.i("onSwipeRight")
            onSwipeToRightAnimation()
        }

        override fun onSwipeLeft() {
            Timber.i("onSwipeLeft")
            // empty
            onSwipeToLeftAnimation()
        }

        override fun onSwipeTop() {
            // empty
        }

        override fun onSwipeBottom() {
            //empty
        }

        override fun onSingleTap() {
            onClickMainLayout()
        }
    }

    private fun onDeleteInstalledPreset() {
        Timber.i("onDeleteInstalledPreset")
        onSwipeToRightAnimation()
    }

    companion object {
        private const val SCALE_MIN = 1.0f
        private const val SCALE_MAX = 1.3f
        private const val ANIMATION_DURATION = 240L
    }

}
