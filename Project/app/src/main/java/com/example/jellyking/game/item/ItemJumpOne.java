package com.example.jellyking.game.item;

import android.graphics.Canvas;

import com.example.jellyking.R;
import com.example.jellyking.framework.Sprite;

public class ItemJumpOne extends Sprite {
    public ItemJumpOne(float x, float y) {
        super(x, y, R.dimen.block_radius, R.mipmap.item_jump_one);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {

    }
}
