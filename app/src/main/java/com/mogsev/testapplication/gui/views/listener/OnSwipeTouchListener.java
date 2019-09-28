package com.mogsev.testapplication.gui.views.listener;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import timber.log.Timber;

/**
 * Created by Eugene Sikaylo
 * email: mogsev@gmail.com
 */
public abstract class OnSwipeTouchListener implements View.OnTouchListener {

    private final float SWIPE_TRANSLATION = 15.0F;
    private final GestureDetector mGestureDetector;
    private float mLastPosition = 0.0F;
    private float mOffsetX = 0.0F;
    private float mDiff = 0.0F;
    private boolean mMoving = false;

    public OnSwipeTouchListener(Context ctx) {
        mGestureDetector = new GestureDetector(ctx, new GestureListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //Timber.i("onTouch: %s", event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mMoving = true;
                mLastPosition = 0.0f;
                mOffsetX = 0.0f;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mMoving) {
                    //Timber.i("Offset info: %s, %s", mLastPosition, event.getX());
                    if (mLastPosition != 0.0f) mDiff = mLastPosition - event.getX();
                    mLastPosition = event.getX();
                    mOffsetX = mOffsetX + mDiff;
                }
                //onCheckOffset();
                break;
            case MotionEvent.ACTION_UP:
                onCheckOffset();
                mMoving = false;
                break;
            case MotionEvent.ACTION_CANCEL:
                onCheckOffset();
                mMoving = false;
                break;
        }

        return mGestureDetector.onTouchEvent(event);
    }

    private void onCheckOffset() {
        Timber.i("onCheckOffset: %s", mOffsetX);
        if (mOffsetX > SWIPE_TRANSLATION ) {
            //Timber.i("Swipe to left");
            onSwipeLeft();
        } else if (mOffsetX < -SWIPE_TRANSLATION) {
            //Timber.i("Swipe to right");
            onSwipeRight();
        }
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 20;
        private static final int SWIPE_VELOCITY_THRESHOLD = 20;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //Timber.i("onFling");
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                        result = true;
                    }
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                    result = true;
                }
            } catch (Exception ex) {
                Timber.e(ex);
            }

            return result;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            //Timber.i("onSingleTapConfirmed");
            onSingleTap();
            return true;
        }
    }

    public abstract void onSwipeRight();

    public abstract void onSwipeLeft();

    public abstract void onSwipeTop();

    public abstract void onSwipeBottom();

    public abstract void onSingleTap();

}
