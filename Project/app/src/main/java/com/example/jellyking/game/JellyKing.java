package com.example.jellyking.game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import com.example.jellyking.R;
import com.example.jellyking.framework.BoxCollidable;
import com.example.jellyking.framework.Metrics;
import com.example.jellyking.framework.Sprite;

public class JellyKing extends Sprite implements BoxCollidable {
    private static final String TAG = JellyKing.class.getSimpleName();

    private static final float MOVE_WIDTH_LIMIT_SHORT = 200.0f;
    private static final float MOVE_WIDTH_LIMIT_LONG = 400.0f;

    private float dx, dy;

    private float jumpHeight = 0.0f;
    private float jumpHeightLimit = 70.0f;
    boolean jumpUp = false;

    private float moveWidth = 0.0f;
    private float moveWidthLimit = MOVE_WIDTH_LIMIT_SHORT;
    boolean move = false;
    boolean moveRight;

    boolean collisionBlock = false;

    boolean collisionStraightLeftBlock = false;
    float collisionStraightLeftBlockY;
    boolean collisionStraightRightBlock = false;
    float collisionStraightRightBlockY;

    private float touchTime = 0.0f;
    boolean touch = false;

    protected RectF boundingBox = new RectF();  // boundingBox
    protected RectF boundingBoxHead = new RectF();
    protected RectF boundingBoxFoot = new RectF();
    protected RectF boundingBoxLeft = new RectF();
    protected RectF boundingBoxRight = new RectF();

    public JellyKing(float x, float y) {
        super(x, y, R.dimen.jellyking_radius, R.mipmap.jellyking_pink);  // jellyKing 생성
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;

        dx = Metrics.size(R.dimen.jellyking_move_speed);
        dy = Metrics.size(R.dimen.jellyking_jump_speed);

        float dx = this.dx * frameTime;
        float dy = this.dy * frameTime;

        /* 터치 시간 구하기 */
        if(touch == true) {  // 터치 중인 경우
            touchTime += frameTime;
        }
        else {  // 터치가 끝났을 경우
            touchTime = 0;
        }

        /* 직진 블록 */
        if(collisionStraightLeftBlock == true) {  // 왼쪽으로 직진하는 블록과 충돌했을 경우
            if(dx >= 0) {  // 왼쪽으로 이동
                dx = -dx;
            }
            dy = collisionStraightLeftBlockY - y;  // 충돌 위치로 y 고정

            if(collisionBlock == true) {  // 벽돌과 충돌했을 경우
                collisionStraightLeftBlock = false;
            }
        }
        if(collisionStraightRightBlock == true) {  // 오른쪽으로 직진하는 블록과 충돌했을 경우
            dy = collisionStraightRightBlockY - y;  // 충돌 위치로 y 고정

            if(collisionBlock == true) {  // 벽돌과 충돌했을 경우
                collisionStraightRightBlock = false;
            }
        }


        /* 점프 */
        if(jumpUp == true) {  // 위로 이동 중인 경우
            // Log.d(TAG, "jumpHeight: " + jumpHeight + " dy:" +  dy);
            jumpHeight += dy;
            if (jumpHeight > jumpHeightLimit) {  // 위에 닿았을 경우
                jumpHeight = 0;
                jumpUp = false;  // 아래로 이동하도록
            }
            if(dy > 0) {
                dy = -dy;
            }
        }

        /* 터치시 이동 */
        if(touch == true)  {  // 터치한 경우
            if(touchTime > 0.5f) {  // 터치 시간이 긴 경우
                moveWidthLimit = MOVE_WIDTH_LIMIT_LONG;  // 이동 거리 늘림
            }
            else {  // 터치 시간이 짧은 경우
                moveWidthLimit = MOVE_WIDTH_LIMIT_SHORT;  // 이동 거리 짧게
            }

            if(move == false) {
                moveWidth = 0.0f;
            }
            else {
                if (moveRight == true) {  // 오른쪽으로 이동하는 경우
                    if (moveWidth > moveWidthLimit) {   // 이동 거리를 도달했을 경우
                        move = false;  // 이동 멈춤.
                        if(collisionBlock == true) {
                            dx = Metrics.size(R.dimen.jellyking_move_speed);
                            moveRight = true;
                            moveWidth = 0.0f;
                            collisionBlock = false;
                        }
                    }
                } else {  // 왼쪽으로 이동하는 경우
                    if (moveWidth < 0) {  // 이동 거리를 도달했을 경우
                        move = false;
                        if(collisionBlock == true) {
                            dx = -(Metrics.size(R.dimen.jellyking_move_speed));
                            moveRight = false;
                            moveWidth = moveWidthLimit;
                            collisionBlock = false;
                        }
                    }
                }
            }
        }
        else if(touch == false && collisionStraightLeftBlock == false && collisionStraightRightBlock == false){  // 터치하지 않았을 경우
            dx = 0;  // 이동하지 않음
        }

        dstRect.offset(dx, dy);
        x += dx;
        y += dy;
        moveWidth += dx;

        /* boundingBox */
        float widthRadius = Metrics.size(R.dimen.jellyking_radius);
        boundingBox.set(x - widthRadius, y - widthRadius, x + widthRadius, y - widthRadius);
        boundingBoxHead.set(x - widthRadius, y - widthRadius, x + widthRadius, y - widthRadius / 2);
        boundingBoxFoot.set(x - widthRadius, y + widthRadius / 2, x + widthRadius, y + widthRadius);
        boundingBoxLeft.set(x - widthRadius, y - widthRadius / 2, x - widthRadius / 2, y + widthRadius / 2);
        boundingBoxRight.set(x + widthRadius / 2, y - widthRadius / 2, x + widthRadius, y + widthRadius / 2);
    }

    public void setMoveDirection(boolean right, boolean collision) {
        move = true;
        collisionBlock = collision;

        if(right == true) {  // 오른쪽 터치를 했을 경우
            dx = Metrics.size(R.dimen.jellyking_move_speed);
            moveRight = true;
            moveWidth = 0.0f;
        }
        else {  // 왼쪽 터치를 했을 경우
            dx = -(Metrics.size(R.dimen.jellyking_move_speed));
            moveRight = false;
            moveWidth = moveWidthLimit;
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
