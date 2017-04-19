package yurasik.com.eventview;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;

public class StateView extends View {
    private static final String TAG = "StateView";
    private boolean isActive;
    private StateEventListener stateEventListener;

    public StateView(Context context) {
        this(context, null);
    }

    public StateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutParams(new ViewGroup.LayoutParams(1,1));
    }

    public interface StateEventListener {
        void onStart();
        void onStop();
    }

    public void setStateEventListener(StateEventListener stateEventListener) {
        this.stateEventListener = stateEventListener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG, "onFinishInflate");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow");
        onStateChanged(true);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow");
        onStateChanged(false);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable save = super.onSaveInstanceState();
        onStateChanged(false);
        Log.d(TAG, "onSaveInstanceState, args: " + save);
        return save;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Log.d(TAG, "onRestoreInstanceState, args: " + state);
        super.onRestoreInstanceState(state);
        onStateChanged(true);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        Log.d(TAG, "onWindowFocusChanged, status: " + hasWindowFocus);
        super.onWindowFocusChanged(hasWindowFocus);
        onStateChanged(hasWindowFocus);
    }

    @Override
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        Log.d(TAG, "onStartTemporaryDetach");

    }

    @Override
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        Log.d(TAG, "onFinishTemporaryDetach");

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG, "onLayout, changed: " + changed);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw");
    }

    @Override
    public void onWindowSystemUiVisibilityChanged(int visible) {
        super.onWindowSystemUiVisibilityChanged(visible);
        Log.d(TAG, "onWindowSystemUiVisibilityChanged: " + visible);
    }

    @Override
    public WindowInsets computeSystemWindowInsets(WindowInsets in, Rect outLocalInsets) {
        Log.d(TAG, "computeSystemWindowInsets: " + outLocalInsets);
        return super.computeSystemWindowInsets(in, outLocalInsets);
    }

    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);
        Log.d(TAG, "onScreenStateChanged");
        switch (screenState) {
            case SCREEN_STATE_ON:
                if (isShown()) {
                    onStateChanged(true);
                }
                break;
            case SCREEN_STATE_OFF:
                if (isShown()) {
                    onStateChanged(false);
                }
                break;
        }
    }

    @Override
    public boolean isShown() {
        return super.isShown();
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged");
    }

    private void onStateChanged(boolean change) {
        if (isActive!=change) {
            isActive = !isActive;
            if (isActive) {
                stateEventListener.onStart();
            } else {
                stateEventListener.onStop();
            }
        }
    }
}
