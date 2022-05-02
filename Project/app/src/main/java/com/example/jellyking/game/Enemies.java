package com.example.jellyking.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.jellyking.R;
import com.example.jellyking.framework.BitmapPool;
import com.example.jellyking.framework.BoxCollidable;
import com.example.jellyking.framework.Metrics;
import com.example.jellyking.framework.Sprite;

public class Enemies  extends Sprite implements BoxCollidable {
    private static final String TAG = Enemies.class.getSimpleName();

    protected static int[] bitmapIds =  {
            R.mipmap.enemy_fix_1, R.mipmap.enemy_fix_2,
            R.mipmap.enemy_drop_1, R.mipmap.enemy_drop_2,
            R.mipmap.enemy_move_1, R.mipmap.enemy_move_2,
            R.mipmap.enemy_move_lr
    };

    protected static int[] radiusSizes =  {
            R.dimen.enemy_radius,
            R.dimen.fix_enemy_width, R.dimen.fix_enemy_height
    };

    protected float startX;
    protected float startY;
    protected int width;
    protected int height;
    protected int enemyType;

    protected int bitmapId1, bitmapId2;
    protected float dx, dy;
    protected float limitX, limitY;
    protected boolean moveUp, moveRight;

    protected RectF boundingBox = new RectF();  // boundingBox
    protected RectF boundingBoxHead = new RectF();

    private float elapsedTimeForChangeImg;
    private float changeImgInterval = 1.0f / 2;

    public Enemies(float x, float y, int w, int h, int bitmapId, int enemyNum) {
        super(x, y, radiusSizes[w], radiusSizes[h], bitmapIds[bitmapId]);

        startX = x;
        startY = y;
        width = w;
        height = h;
        enemyType = enemyNum;

        switch (enemyType) {
            case 1:  // FixEnemy
                bitmapId1 = bitmapIds[0];
                bitmapId2 = bitmapIds[1];
                break;
            case 2:  // DropEnemy
                bitmapId1 = bitmapIds[2];
                bitmapId2 = bitmapIds[3];
                limitY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 8);
                break;
            case 3:  // MoveUDEnemy
                bitmapId1 = bitmapIds[4];
                bitmapId2 = bitmapIds[5];
                limitY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 8);
                moveUp = false;
                break;
            case 4:  // MoveLREnemy
                bitmapId1 = bitmapIds[6];
                limitX = Metrics.width / 26 * (3 + 15);
                moveRight = false;
                break;
        }

        changeImgInterval = Metrics.floatValue(R.dimen.enemy_change_interval);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;

        /* 애니메이션 */
        if(enemyType != 4) {  // FixEnemy, DropEnemy, MoveUDEnemy
            elapsedTimeForChangeImg += frameTime;
            if (elapsedTimeForChangeImg >= changeImgInterval) {
                bitmap = BitmapPool.get(bitmapId1);
                elapsedTimeForChangeImg -= changeImgInterval;
            } else {
                bitmap = BitmapPool.get(bitmapId2);
            }
        }

        /* 움직임 */
        switch (enemyType) {
            case 2:  // DropEnemy
                drop(frameTime);
                break;
            case 3:  // MoveUDEnemy
                moveUD(frameTime);
                break;
            case 4:  // MoveLREnemy
                moveLR(frameTime);
                break;
        }

        /* boundingBox */
        float widthRadius = Metrics.size(radiusSizes[width]);
        float heightRadius = Metrics.size(radiusSizes[height]);
        boundingBox.set(x - widthRadius, y - heightRadius, x + widthRadius, y + heightRadius);
        boundingBoxHead.set(x - widthRadius, y - heightRadius, x + widthRadius, y - heightRadius / 2);
    }

    public void drop(float frameTime) {
        dy = Metrics.size(R.dimen.drop_enemy_speed);

        float dy = this.dy * frameTime;

        if(y > limitY) {
            MainGame.getInstance().remove(this);
        }

        dstRect.offset(0, dy);
        y += dy;
    }

    public void moveUD(float frameTime) {
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
            if(y > limitY) {  // 이동 거리 완주한 경우
                moveUp = true;
            }
        }

        dstRect.offset(0, dy);
        y += dy;
    }

    public void moveLR(float frameTime){
        dx = Metrics.size(R.dimen.move_enemy_speed);

        float dx = this.dx * frameTime;

        if(moveRight == true) {  // 오른쪽으로 이동하는 경우
            if(x > limitX) {  // 이동 거리 완주한 경우
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
    }

    public void death() {
        if(enemyType == 4) {  // MoveLREnemy
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