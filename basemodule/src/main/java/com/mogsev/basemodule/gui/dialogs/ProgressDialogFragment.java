package com.mogsev.basemodule.gui.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.mogsev.basemodule.R;

import timber.log.Timber;

/**
 * Created by Eugene Sikaylo
 * email: mogsev@gmail.com
 */

public class ProgressDialogFragment extends FullScreenDialogFragment {
    public static final String TAG = ProgressDialogFragment.class.getSimpleName();

    public static ProgressDialogFragment newInstance() {
        return new ProgressDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Timber.i("onCreateView");
        View view = inflater.inflate(R.layout.fragment_dialog_progress, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Timber.i("onViewCreated");
        setCancelable(false);
    }

}
