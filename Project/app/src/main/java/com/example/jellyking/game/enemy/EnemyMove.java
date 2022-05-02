package com.example.jellyking.game.enemy;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import com.example.jellyking.R;
import com.example.jellyking.framework.BitmapPool;
import com.example.jellyking.framework.BoxCollidable;
import com.example.jellyking.framework.Metrics;
import com.example.jellyking.framework.Sprite;
import com.example.jellyking.game.MainGame;

public class EnemyMove extends Sprite implements BoxCollidable {
    private static final String TAG = EnemyMove.class.getSimpleName();

    protected RectF boundingBox = new RectF();  // boundingBox

    private float elapsedTimeForChangeImg;
    private float changeImgInterval = 1.0f / 2;

    private float dy;
    private float startY;
    private float moveHeightLimit = Metrics.height / 13 * 3 + (Metrics.height / 13 * 8);
    boolean moveUp = false;

    public EnemyMove(float x, float y) {
        super(x, y, R.dimen.enemy_radius, R.mipmap.enemy_move_1);

        startY = y;

        changeImgInterval = Metrics.floatValue(R.dimen.enemy_change_interval);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;

        /* 애니메이션 */
        elapsedTimeForChangeImg += frameTime;
        if(elapsedTimeForChangeImg >= changeImgInterval) {
            bitmap = BitmapPool.get(R.mipmap.enemy_move_2);
            elapsedTimeForChangeImg -= changeImgInterval;
        }
        else {
            bitmap = BitmapPool.get(R.mipmap.enemy_move_1);
        }

        /* 이동 */
        dy = Metrics.size(R.dimen.move_enemy_speed);

        float dy = this.dy * frameTime;

        if(moveUp == true) {  // 위로 이동하는 경우
            if(y < startY) {  // 이동 거리 완주한 경우
                moveUp = false;
            }
            if(dy > 0) {
                dy = -dy;
            }
        }
        else {
            if(y > moveHeightLimit) {  // 이동 거리 완주한 경우
                moveUp = true;
            }
        }

        dstRect.offset(0, dy);
        y += dy;

        /* boundingBox */
        float widthRadius = Metrics.size(R.dimen.enemy_radius);
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
