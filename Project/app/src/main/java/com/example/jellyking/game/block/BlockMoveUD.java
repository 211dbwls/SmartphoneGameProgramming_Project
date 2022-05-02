package com.example.jellyking.game.block;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import com.example.jellyking.R;
import com.example.jellyking.framework.BoxCollidable;
import com.example.jellyking.framework.Metrics;
import com.example.jellyking.framework.Sprite;
import com.example.jellyking.game.MainGame;

public class BlockMoveUD extends Sprite implements BoxCollidable {
    private static final String TAG = BlockMoveUD.class.getSimpleName();

    protected RectF boundingBoxHead = new RectF();  // boundingBox

    private float dy;
    private float startY;
    private float moveHeightLimit = Metrics.height / 13 * 3 + (Metrics.height / 13 * 0);
    boolean moveUp = true;

    public BlockMoveUD(float x, float y) {
        super(x, y, R.dimen.block_radius, R.mipmap.block_1);

        startY = y;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;

        dy = Metrics.size(R.dimen.move_lr_block_speed);

        float dy = this.dy * frameTime;

        if(moveUp == true) {  // 위로 이동하는 경우
            if(y < moveHeightLimit) {  // 이동 거리 완주한 경우
                moveUp = false;
            }
            if(dy > 0) {
                dy = -dy;
            }
        }
        else {
            if(y > startY) {  // 이동 거리 완주한 경우
                moveUp = true;
            }
        }

        dstRect.offset(0, dy);
        y += dy;

        /* boundingBox */
        float widthRadius = Metrics.size(R.dimen.block_radius);
        boundingBoxHead.set(x - widthRadius, y - widthRadius, x + widthRadius, y - widthRadius / 2);
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
