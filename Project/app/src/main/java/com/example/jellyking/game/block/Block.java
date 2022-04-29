package com.example.jellyking.game.block;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.jellyking.R;
import com.example.jellyking.framework.BoxCollidable;
import com.example.jellyking.framework.Metrics;
import com.example.jellyking.framework.Sprite;

public class Block extends Sprite implements BoxCollidable {
    protected RectF boundingBoxHead = new RectF();  // boundingBox
    protected RectF boundingBoxFoot = new RectF();
    protected RectF boundingBoxLeft = new RectF();
    protected RectF boundingBoxRight = new RectF();

    public Block(float x, float y) {
        super(x, y, R.dimen.block_radius, R.mipmap.block_1);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {
        /* boundingBox */
        float widthRadius = Metrics.size(R.dimen.block_radius);
        boundingBoxHead.set(x - widthRadius, y - widthRadius, x + widthRadius, y - widthRadius / 2);
        boundingBoxFoot.set(x - widthRadius, y + widthRadius / 2, x + widthRadius, y + widthRadius);
        boundingBoxLeft.set(x - widthRadius, y - widthRadius / 2, x - widthRadius / 2, y + widthRadius / 2);
        boundingBoxRight.set(x + widthRadius / 2, y - widthRadius / 2, x + widthRadius, y + widthRadius / 2);
    }

    @Override
    public RectF getBoundingRect() {
        return null;
    }

    @Override
    public RectF getBoundingRectHead() {
        return boundingBoxHead;
    }

    @Override
    public RectF getBoundingRectFoot() {
        return boundingBoxFoot;
    }

    @Override
    public RectF getBoundingRectLeft() {
        return boundingBoxLeft;
    }

    @Override
    public RectF getBoundingRectRight() {
        return boundingBoxRight;
    }
}
