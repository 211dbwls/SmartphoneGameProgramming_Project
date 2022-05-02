package com.example.jellyking.game.block;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import com.example.jellyking.R;
import com.example.jellyking.framework.BoxCollidable;
import com.example.jellyking.framework.Metrics;
import com.example.jellyking.framework.Sprite;
import com.example.jellyking.game.MainGame;

public class BlockMoveLR extends Sprite implements BoxCollidable {
    private static final String TAG = BlockMoveLR.class.getSimpleName();

    protected RectF boundingBoxHead = new RectF();  // boundingBox

    private float dx;
    private float startX;
    private float moveWidthLimit = Metrics.height / 13 * 3 + (Metrics.height / 13 * 16);
    boolean moveRight = true;

    public BlockMoveLR(float x, float y) {
        super(x, y, R.dimen.move_lr_block_width_radius, R.dimen.move_lr_block_height_radius, R.mipmap.block_3);

        startX = x;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;

        dx = Metrics.size(R.dimen.move_lr_block_speed);

        float dx = this.dx * frameTime;

        if(moveRight == true) {  // 오른쪽으로 이동하는 경우
            if(x > moveWidthLimit) {  // 이동 거리 완주한 경우
                moveRight = false;
            }
        }
        else {
            if(x < startX) {  // 이동 거리 완주한 경우
                moveRight = true;
            }
            if(dx > 0) {
                dx = -dx;
            }
        }

        dstRect.offset(dx, 0);
        x += dx;

        /* boundingBox */
        float widthRadius = Metrics.size(R.dimen.move_lr_block_width_radius);
        float heightRadius = Metrics.size(R.dimen.move_lr_block_height_radius);
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
