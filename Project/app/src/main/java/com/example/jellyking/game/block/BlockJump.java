package com.example.jellyking.game.block;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.jellyking.R;
import com.example.jellyking.framework.BitmapPool;
import com.example.jellyking.framework.BoxCollidable;
import com.example.jellyking.framework.Metrics;
import com.example.jellyking.framework.Sprite;
import com.example.jellyking.game.MainGame;

public class BlockJump extends Sprite implements BoxCollidable {
    protected RectF boundingBoxHead = new RectF();  // boundingBox

    public boolean collision = false;

    private float elapsedTimeForChangeImg;
    private float changeImgInterval = 1.0f / 2;

    public BlockJump(float x, float y) {
        super(x, y, R.dimen.block_radius, R.mipmap.block_jump_2);

        changeImgInterval = Metrics.floatValue(R.dimen.jump_block_change_interval);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;

        /* 충돌 시 애니메이션 */
        if(collision == true) {
            bitmap = BitmapPool.get(R.mipmap.block_jump_1);
            elapsedTimeForChangeImg += frameTime;
            if(elapsedTimeForChangeImg >= changeImgInterval) {
                collision = false;
                elapsedTimeForChangeImg -= changeImgInterval;
            }
        }
        else {
            bitmap = BitmapPool.get(R.mipmap.block_jump_2);
        }

        /* boundingBox */
        float widthRadius = Metrics.size(R.dimen.block_radius);
        float heightRadius = Metrics.size(R.dimen.jump_block_height_radius);
        boundingBoxHead.set(x - widthRadius, y - heightRadius, x + widthRadius, y - heightRadius / 2);
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
