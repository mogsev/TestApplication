package com.mogsev.basemodule.gui.dialogs;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import timber.log.Timber;

/**
 * Created by Eugene Sikaylo
 * email: mogsev@gmail.com
 */

public class FullScreenDialogFragment extends DialogFragment {

    protected Drawable mDrawable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("onCreate");
        setStyle(DialogFragment.STYLE_NO_FRAME, 0);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Timber.i("onCreateDialog");
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Timber.i("onStart");
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }

    protected void setBackground(@NonNull ViewGroup viewGroup) {
        Timber.i("setBackground");
        if (viewGroup == null) {
            throw new IllegalArgumentException("ViewGroup cannot be null");
        }
        if (mDrawable != null) {
            viewGroup.setBackground(mDrawable);
        }
    }

    public void setDrawableForBackground(@Nullable Drawable drawable) {
        mDrawable = drawable;
    }

}
