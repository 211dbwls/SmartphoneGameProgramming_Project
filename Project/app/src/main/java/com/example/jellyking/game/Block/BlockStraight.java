package com.example.jellyking.game.Block;

import android.graphics.Canvas;

import com.example.jellyking.R;
import com.example.jellyking.framework.Sprite;

public class BlockStraight extends Sprite {
    public BlockStraight(float x, float y) {
        super(x, y, R.dimen.block_radius, R.mipmap.block_grass_1);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {

    }
}