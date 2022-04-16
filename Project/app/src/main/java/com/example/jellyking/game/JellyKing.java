package com.example.jellyking.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.jellyking.R;
import com.example.jellyking.framework.Sprite;

public class JellyKing extends Sprite {
    private static final String TAG = JellyKing.class.getSimpleName();
    private RectF targetRect = new RectF();

    private float dx, dy;
    private float tx, ty;

    public JellyKing(float x, float y) {
        super(x, y, R.dimen.jellyking_radius, R.mipmap.jellyking);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;

        if (dx == 0)
            return;

        float dx = this.dx * frameTime;
        boolean arrived = false;

        if ((dx > 0 && x + dx > tx) || (dx < 0 && x + dx < tx)) {
            dx = tx - x;
            x = tx;
            arrived = true;
        }
        else {
            x += dx;
        }

        dstRect.offset(dx, 0);

        if(arrived) {
            this.dx = 0;
        }
    }

}
