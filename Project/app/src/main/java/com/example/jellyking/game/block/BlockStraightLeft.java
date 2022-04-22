package com.example.jellyking.game.block;

import android.graphics.Canvas;

import com.example.jellyking.R;
import com.example.jellyking.framework.Sprite;

public class BlockStraightLeft extends Sprite {
    public BlockStraightLeft(float x, float y) {
        super(x, y, R.dimen.block_radius, R.mipmap.block_straight_left);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {

    }
}