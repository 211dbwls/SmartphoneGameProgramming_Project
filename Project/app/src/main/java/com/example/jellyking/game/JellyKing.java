package com.example.jellyking.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.jellyking.R;
import com.example.jellyking.framework.Sprite;

public class JellyKing extends Sprite {
    private static final String TAG = JellyKing.class.getSimpleName();

    private float dx, dy;
    private float tx, ty;

    public JellyKing(float x, float y) {
        super(x, y, R.dimen.jellyking_radius, R.mipmap.jellyking_pink);  // jellyKing 생성
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;
    }

}
