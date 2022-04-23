package com.example.jellyking.game.item;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.jellyking.R;
import com.example.jellyking.framework.BoxCollidable;
import com.example.jellyking.framework.Metrics;
import com.example.jellyking.framework.Sprite;

public class ItemJumpOne extends Sprite implements BoxCollidable {
    protected RectF boundingBox = new RectF();  // boundingBox

    public ItemJumpOne(float x, float y) {
        super(x, y, R.dimen.block_radius, R.mipmap.item_jump_one);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update(){
        /* boundingBox */
        float widthRadius = Metrics.size(R.dimen.block_radius);
        boundingBox.set(x - widthRadius, y - widthRadius, x + widthRadius, y + widthRadius);
    }

    @Override
    public RectF getBoundingRect() {
        return boundingBox;
    }
}
