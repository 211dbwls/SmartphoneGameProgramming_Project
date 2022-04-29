package com.example.jellyking.game.block;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.jellyking.R;
import com.example.jellyking.framework.BoxCollidable;
import com.example.jellyking.framework.Metrics;
import com.example.jellyking.framework.Sprite;

public class BlockJump extends Sprite implements BoxCollidable {
    protected RectF boundingBox = new RectF();  // boundingBox

    public BlockJump(float x, float y) {
        super(x, y, R.dimen.block_radius, R.mipmap.block_jump_2);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {
        /* boundingBox */
        float widthRadius = Metrics.size(R.dimen.block_radius);
        float heightRadius = Metrics.size(R.dimen.jump_block_height_radius);
        boundingBox.set(x - widthRadius, y - heightRadius, x + widthRadius, y + widthRadius);
    }

    @Override
    public RectF getBoundingRect() {
        return boundingBox;
    }

    @Override
    public RectF getBoundingRectHead() {
        return null;
    }

    @Override
    public RectF getBoundingRectFoot() {
        return null;
    }

    @Override
    public RectF getBoundingRectLeft() {
        return null;
    }

    @Override
    public RectF getBoundingRectRight() {
        return null;
    }
}
