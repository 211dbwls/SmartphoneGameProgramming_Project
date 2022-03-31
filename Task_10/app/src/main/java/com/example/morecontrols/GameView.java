package com.example.morecontrols;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GameView extends View {

    private static final String TAG = GameView.class.getSimpleName();

    public GameView(Context context) {
        super(context);
        Log.d(TAG, "GameView cons");
    }

    public GameView(Context context, AttributeSet as) {
        super(context, as);
        Log.d(TAG, "GameView cons with as");
    }
}
