package com.example.jellyking.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.jellyking.R;
import com.example.jellyking.framework.res.BitmapPool;
import com.example.jellyking.framework.interfaces.BoxCollidable;
import com.example.jellyking.framework.res.Metrics;
import com.example.jellyking.framework.object.Sprite;

public class Blocks  extends Sprite implements BoxCollidable {
    protected static int[] bitmapIds =  {
            R.mipmap.block_1,
            R.mipmap.block_broken,
            R.mipmap.block_electric,
            R.mipmap.block_jump_1, R.mipmap.block_jump_2,
            R.mipmap.block_3,
            R.mipmap.block_straight_left, R.mipmap.block_straight_right
    };

    protected static int[] radiusSizes =  {
            R.dimen.block_radius,
            R.dimen.jump_block_height_radius,
            R.dimen.move_lr_block_width_radius, R.dimen.move_lr_block_height_radius
    };

    protected float startX;
    protected float startY;
    protected int width;
    protected int height;

    protected int blockType;

    protected float dx, dy;
    protected float limitX, limitY;
    protected boolean moveUp, moveRight;

    public boolean jumpBlockCollision;

    private float elapsedTimeForChangeImg;
    private float changeImgInterval = 1.0f / 2;

    protected RectF boundingBox = new RectF();  // boundingBox
    protected RectF boundingBoxHead = new RectF();
    protected RectF boundingBoxFoot = new RectF();
    protected RectF boundingBoxLeft = new RectF();
    protected RectF boundingBoxRight = new RectF();

    public Blocks(float x, float y, int w, int h, int bitmapId, int blockNum) {
        super(x, y, radiusSizes[w], radiusSizes[h], bitmapIds[bitmapId]);

        startX = x;
        startY = y;
        width = w;
        height = h;
        blockType = blockNum;

        switch (blockType) {
            case 1:  // Block
                break;
            case 2:  // BrokenBlock
                break;
            case 3:  // ElectricBlock
                break;
            case 4:  // JumpBlock
                changeImgInterval = Metrics.floatValue(R.dimen.jump_block_change_interval);
            case 5:  // MoveLRBlock
                limitX = Metrics.width / 26 * (3 + 16);
                moveRight = true;
                break;
            case 6:  // MoveUDBlock
                limitY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 0);
                moveUp = true;
                break;
            case 7:  // StraightLeftBlock
                break;
            case 8:  // StraightRightBlock
                break;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {
        float frameTime = MainScene.get().frameTime;

        /* 애니메이션 */
        switch (blockType) {
            case 4:  // JumpBlock
                jumpBlockCollision(frameTime, jumpBlockCollision);
                break;
            case 5:  // MoveLRBlock
                moveLR(frameTime);
                break;
            case 6:  // MoveUDBlock
                moveUD(frameTime);
                break;
        }

        /* boundingBox */
        float widthRadius = Metrics.size(radiusSizes[width]);
        float heightRadius = Metrics.size(radiusSizes[height]);
        boundingBox.set(x - widthRadius, y - heightRadius, x + widthRadius, y + heightRadius);
        boundingBoxHead.set(x - widthRadius, y - heightRadius, x + widthRadius, y - heightRadius / 2);
        boundingBoxFoot.set(x - widthRadius, y + heightRadius / 2, x + widthRadius, y + heightRadius);
        boundingBoxLeft.set(x - widthRadius, y - heightRadius / 2, x - widthRadius / 2, y + heightRadius / 2);
        boundingBoxRight.set(x + widthRadius / 2, y - heightRadius / 2, x + widthRadius, y + heightRadius / 2);
    }

    public void jumpBlockCollision(float frameTime, boolean collision) {
        /* 충돌 시 애니메이션 */
        if(collision == true) {
            bitmap = BitmapPool.get(R.mipmap.block_jump_1);
            elapsedTimeForChangeImg += frameTime;
            if(elapsedTimeForChangeImg >= changeImgInterval) {
                jumpBlockCollision = false;
                elapsedTimeForChangeImg -= changeImgInterval;
            }
        }
        else {
            bitmap = BitmapPool.get(R.mipmap.block_jump_2);
        }
    }

    public void moveLR(float frameTime) {
        dx = Metrics.size(R.dimen.move_lr_block_speed);

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

    public void moveUD(float frameTime) {
        dy = Metrics.size(R.dimen.move_lr_block_speed);

        float dy = this.dy * frameTime;

        if(moveUp == true) {  // 위로 이동하는 경우
            if(y < limitY) {  // 이동 거리 완주한 경우
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

