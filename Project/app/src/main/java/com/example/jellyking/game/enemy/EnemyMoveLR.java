package com.example.jellyking.game.enemy;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.jellyking.R;
import com.example.jellyking.framework.BitmapPool;
import com.example.jellyking.framework.BoxCollidable;
import com.example.jellyking.framework.Metrics;
import com.example.jellyking.framework.Sprite;
import com.example.jellyking.game.MainGame;

public class EnemyMoveLR  extends Sprite implements BoxCollidable {
    private static final String TAG = EnemyMove.class.getSimpleName();

    protected RectF boundingBox = new RectF();  // boundingBox
    protected RectF boundingBoxHead = new RectF();

    private float elapsedTimeForChangeImg;
    private float changeImgInterval = 1.0f / 2;

    private float dx;
    private float startX;
    private float moveWidthLimit = Metrics.width / 26 * (3 + 15);
    boolean moveRight = false;

    public EnemyMoveLR(float x, float y) {
        super(x, y, R.dimen.enemy_radius, R.mipmap.enemy_move_lr);

        startX = x;

        changeImgInterval = Metrics.floatValue(R.dimen.enemy_change_interval);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;

        /* 이동 */
        dx = Metrics.size(R.dimen.move_enemy_speed);

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
        float widthRadius = Metrics.size(R.dimen.enemy_radius);
        boundingBox.set(x - widthRadius, y - widthRadius, x + widthRadius, y + widthRadius);
        boundingBoxHead.set(x - widthRadius, y - widthRadius, x + widthRadius, y - widthRadius / 2);
    }

    public void death() {
        float frameTime = MainGame.getInstance().frameTime;

        bitmap = BitmapPool.get(R.mipmap.enemy_move_lr_dead);

        elapsedTimeForChangeImg += frameTime;
        if(elapsedTimeForChangeImg >= changeImgInterval) {
            MainGame.getInstance().remove(this);
        }
    }

    @Override
    public RectF getBoundingRect() {
        return boundingBox;
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

