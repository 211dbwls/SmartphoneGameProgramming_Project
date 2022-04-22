package com.example.jellyking.game;

import android.graphics.Canvas;

import com.example.jellyking.R;
import com.example.jellyking.framework.Sprite;

public class Star extends Sprite {
    public Star(float x, float y) {
        super(x, y, R.dimen.block_radius, R.mipmap.star);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {

    }
}