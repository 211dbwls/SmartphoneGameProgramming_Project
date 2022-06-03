package com.example.jellyking.framework.object;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.MotionEvent;

import com.example.jellyking.framework.interfaces.Touchable;
import com.example.jellyking.framework.res.BitmapPool;

public class Button extends Sprite implements Touchable {
    private static final String TAG = Button.class.getSimpleName();

    protected final Callback callback;

    public enum Action {
        pressed, released,
    }

    public interface Callback {
        public boolean onTouch(Action action);
    }

    public Button(float x, float y, float w, float h, int bitmapResId, Callback callback) {
        super(x, y, w, h, bitmapResId);
        this.callback = callback;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();
        if (!dstRect.contains(x, y)) {
            return false;
        }

        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                return callback.onTouch(Action.pressed);
            case MotionEvent.ACTION_UP:
                return callback.onTouch(Action.released);
        }
        return false;
    }
}
