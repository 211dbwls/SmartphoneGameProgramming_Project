package com.example.jellyking.game.Block;

import android.graphics.Canvas;

import com.example.jellyking.R;
import com.example.jellyking.framework.Sprite;

public class BlockBroken extends Sprite {
    public BlockBroken(float x, float y) {
        super(x, y, R.dimen.block_radius, R.mipmap.block_broken);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {

    }
}
