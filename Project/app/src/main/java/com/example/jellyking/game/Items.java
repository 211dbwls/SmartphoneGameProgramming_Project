package com.example.jellyking.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.jellyking.R;
import com.example.jellyking.framework.interfaces.BoxCollidable;
import com.example.jellyking.framework.res.Metrics;
import com.example.jellyking.framework.object.Sprite;

public class Items  extends Sprite implements BoxCollidable {
    protected static int[] bitmapIds =  {
            R.mipmap.item_jump_one, R.mipmap.item_jump_infinite
    };

    protected int itemType;

    protected RectF boundingBox = new RectF();  // boundingBox

    public Items(float x, float y, int bitmapId, int itemNum) {
        super(x, y, R.dimen.block_radius, bitmapIds[bitmapId]);

        itemType = itemNum;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {
        /* boundingBox */
        float widthRadius = Metrics.size(R.dimen.block_radius);
        boundingBox.set(x - widthRadius, y - widthRadius, x + widthRadius, y + widthRadius);
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