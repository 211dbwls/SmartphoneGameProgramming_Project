package com.example.jellyking.game.block;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.jellyking.R;
import com.example.jellyking.framework.BoxCollidable;
import com.example.jellyking.framework.Metrics;
import com.example.jellyking.framework.Sprite;

public class BlockMoveLR extends Sprite implements BoxCollidable {
    protected RectF boundingBox = new RectF();  // boundingBox

    public BlockMoveLR(float x, float y) {
        super(x, y, R.dimen.block_radius, R.mipmap.block_3);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {
        /* boundingBox */
        float widthRadius = Metrics.size(R.dimen.block_radius);
        boundingBox.set(x - widthRadius, y - widthRadius, x + widthRadius, y);
    }

    @Override
    public RectF getBoundingRect() {
        return boundingBox;
    }
}
