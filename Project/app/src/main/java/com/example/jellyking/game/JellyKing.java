package com.example.jellyking.game;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import com.example.jellyking.R;
import com.example.jellyking.framework.interfaces.BoxCollidable;
import com.example.jellyking.framework.res.Metrics;
import com.example.jellyking.framework.object.Sprite;

import java.util.ArrayList;

public class JellyKing extends Sprite implements BoxCollidable {
    private static final String TAG = JellyKing.class.getSimpleName();

    private static final float MOVE_WIDTH_LIMIT_SHORT = 300.0f;
    private static final float MOVE_WIDTH_LIMIT_LONG = 450.0f;

    private static final float JUMP_HEIGHT_LIMIT_SHORT = 80.0f;
    private static final float JUMP_HEIGHT_LIMIT_LONG = 400.0f;

    private float dx = Metrics.size(R.dimen.jellyking_move_speed);
    private float dy = Metrics.size(R.dimen.jellyking_jump_speed);

    private final float gravity;

    private float jumpHeight = 0.0f;
    private float jumpHeightLimit = JUMP_HEIGHT_LIMIT_SHORT;
    boolean jumpUp = false;

    private final float jumpPower;
    private float jumpSpeed;
    float t;
    boolean jumpingPoint = false;

    private float moveStartX, moveStartY;

    private float moveWidth = 0.0f;
    private float moveWidthLimit = MOVE_WIDTH_LIMIT_SHORT;
    boolean move = false;
    boolean moveRight;

    boolean collisionBlock = false;

    boolean collisionJumpBlock = false;

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

    public int starCount;  // 획득한 별 갯수

    private PointF jellyKingPos = new PointF();

    class Point {
        float x, y;
    }

    Point point1, point2, point3;
    float pDx, pDy;

    public JellyKing(float x, float y) {
        super(x, y, R.dimen.jellyking_radius, R.mipmap.jellyking_pink);  // jellyKing 생성

        gravity = Metrics.size(R.dimen.gravity);
        jumpPower = Metrics.size(R.dimen.jellyking_jump_power);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;

//        float dx = this.dx * frameTime;
//        float dy = jumpSpeed * frameTime;
//        jumpSpeed += gravity * frameTime;
//
//        /* 터치 시간 구하기 */
//        if(touch == true) {  // 터치 중인 경우
//            touchTime += frameTime;
//        }
//        else {  // 터치가 끝났을 경우
//            touchTime = 0;
//        }
//
//        /* 터치시 점프 및 이동 */
//        if(touch == true) {  // 터치한 경우
//            /* 터치 시간에 따라 이동 거리 설정 */
//            if (touchTime > 0.5f) {  // 터치 시간이 긴 경우
//                moveWidthLimit = MOVE_WIDTH_LIMIT_LONG;  // 이동 거리 늘림
//            } else {  // 터치 시간이 짧은 경우
//                moveWidthLimit = MOVE_WIDTH_LIMIT_SHORT;  // 이동 거리 짧게
//            }
//
//            if (move == true) {  // 이동하는 경우
//                if (moveRight == true) {  // 오른쪽으로 이동하는 경우
//                    if (jumpingPoint == true) {   // 점프 처음할 때 점 구하기
//                        Point point1 = new Point();
//                        point1.x = moveStartX;
//                        point1.y = moveStartY;
//
//                        Point point2 = new Point();
//                        point2.x = moveStartX + moveWidthLimit / 2;
//                        point2.y = moveStartY + jumpHeightLimit;
//
//                        Point point3 = new Point();
//                        point3.x = moveStartX + moveWidthLimit;
//                        point3.y = moveStartY;
//
//                        jumpingPoint = false;
//                        t = 0;
//                    }
//
//                    pDx = (float)(Math.pow((1 - t), 2) * point1.x + 2 * (1 - t) * t * point2.x + Math.pow(t, 2) * point3.x);
//                    pDy = (float)(Math.pow((1 - t), 2) * point1.y + 2 * (1 - t) * t * point2.y + Math.pow(t, 2) * point3.y);
//
//                    t += 0.1;
//                    if (t == 1) {  // 점프 끝났을 때
//                        move = false;
//                    }
//                    /*if (moveWidth > moveWidthLimit) {   // 이동 거리를 도달했을 경우
//                        if(collisionBlock == true) {
//                            moveRight = true;
//                            moveWidth = 0.0f;
//                            collisionBlock = false;
//                        }
//                        move = false;  // 이동 멈춤.
//                    }
//                    if(dx < 0) {
//                        dx = -dx;
//                    }*/
//                }
//                else {  // 왼쪽으로 이동하는 경우
//                    if (moveWidth < 0) {  // 이동 거리를 도달했을 경우
//                        if (collisionBlock == true) {
//                            moveRight = false;
//                            moveWidth = moveWidthLimit;
//                            collisionBlock = false;
//                        }
//                        move = false;
//                    }
//                    if (dx > 0) {
//                        dx = -dx;
//                    }
//                }
//            }
//            else {  // 이동하지 않는 경우
//                moveWidth = 0.0f;
//            }
//        }
//        else if(touch == false && collisionStraightLeftBlock == false && collisionStraightRightBlock == false){  // 터치하지 않았을 경우
//            /* 제자리 점프 */
//            if(jumpUp == true) {  // 위로 이동 중인 경우
//                dx = 0;  // 좌우로 이동하지 않도록
//                jumpSpeed = -jumpPower;  // jumpSpeed 설정
//                jumpHeight += dy;
//                if (jumpHeight < -jumpHeightLimit) {  // 위에 닿았을 경우
//                    jumpUp = false;  // 아래로 이동하도록
//                }
//            }
//            else {
//                dx = 0;  // 좌우로 이동하지 않도록
//                jumpSpeed = jumpPower;
//                jumpHeight += dy;
//            }
//        }
//
//        x += dx;
//        y += dy;
//        dstRect.offset(dx, dy);
//        moveWidth += dx;

        float dx = this.dx * (float)2.5 * frameTime;
        float dy = this.dy * (float)1.5 * frameTime;

        /* 터치 시간 구하기 */
        if(touch == true) {  // 터치 중인 경우
            touchTime += frameTime;
        }
        else {  // 터치가 끝났을 경우
            touchTime = 0;
        }

        /* 직진 블록과 충돌한 경우 */
        if(collisionStraightLeftBlock == true) {
            if(dx >= 0) {  // 왼쪽으로 이동
                dx = -dx;
            }
            dy = collisionStraightLeftBlockY - y;  // 충돌 위치로 y 고정

            if(touch == true) {  // 이동 중 터치했을 경우
                collisionStraightLeftBlock = false;
                dy = this.dy * frameTime;
            }
            else if(collisionBlock == true) {  // 벽돌과 충돌했을 경우
                collisionStraightLeftBlock = false;
            }
        }
        else if(collisionStraightRightBlock == true) {
            dy = collisionStraightRightBlockY - y;  // 충돌 위치로 y 고정

            if(touch == true) {  // 이동 중 터치했을 경우
                collisionStraightRightBlock = false;
                dy = this.dy * frameTime;
            }
            else if(collisionBlock == true) {  // 벽돌과 충돌했을 경우
                collisionStraightRightBlock = false;
            }
        }

        /* 점프 블록과 충돌한 경우 */
        if(collisionJumpBlock == true) {
            jumpHeightLimit = JUMP_HEIGHT_LIMIT_LONG;
            collisionJumpBlock = false;
        }
        else if(collisionJumpBlock == false && jumpUp == false){
            jumpHeightLimit = JUMP_HEIGHT_LIMIT_SHORT;
        }

        /* 점프 */
        if(jumpUp == true) {  // 위로 이동 중인 경우
            jumpHeight += dy;
            if (jumpHeight > jumpHeightLimit) {  // 위에 닿았을 경우
                jumpHeight = 0;
                jumpUp = false;  // 아래로 이동하도록
            }
            if(dy > 0) {
                dy = -dy;
            }
        }

        /* 터치 */
        if(touch == true)  {  // 터치한 경우
            /* 터치 시간에 따라 이동 거리 설정 */
            if(touchTime > 0.5f) {  // 터치 시간이 긴 경우
                moveWidthLimit = MOVE_WIDTH_LIMIT_LONG;  // 이동 거리 늘림
            }
            else {  // 터치 시간이 짧은 경우
                moveWidthLimit = MOVE_WIDTH_LIMIT_SHORT;  // 이동 거리 짧게
            }

            if(move == true) {  // 이동하는 경우
                if (moveRight == true) {  // 오른쪽으로 이동하는 경우
                    if (moveWidth > moveWidthLimit) {   // 이동 거리를 도달했을 경우
                        if(collisionBlock == true) {
                            moveRight = true;
                            moveWidth = 0.0f;
                            collisionBlock = false;
                        }
                        move = false;  // 이동 멈춤.
                    }
                    if(dx < 0) {
                        dx = -dx;
                    }
                }
                else {  // 왼쪽으로 이동하는 경우
                    if (moveWidth < 0) {  // 이동 거리를 도달했을 경우
                        if(collisionBlock == true) {
                            moveRight = false;
                            moveWidth = moveWidthLimit;
                            collisionBlock = false;
                        }
                        move = false;
                    }
                    if(dx > 0) {
                        dx = -dx;
                    }
                }
            }
            else {
                moveWidth = 0.0f;
            }
        }
        else if(touch == false && collisionStraightLeftBlock == false && collisionStraightRightBlock == false){  // 터치하지 않았을 경우
            dx = 0;  // 이동하지 않음
        }

        dstRect.offset(dx, dy);
        x += dx;
        y += dy;
        moveWidth += dx;

        /* 떨어졌을 경우 죽도록 */
        if(y > Metrics.height) {
            death();
        }

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
        moveStartX = x;
        moveStartY = y;

       if (right == true && collision == false) {  // 오른쪽 터치를 했을 경우
           dx = Metrics.size(R.dimen.jellyking_move_speed);
           moveRight = true;
           moveWidth = 0.0f;
       }
       else if(right == false && collision == false){  // 왼쪽 터치를 했을 경우
           dx = -(Metrics.size(R.dimen.jellyking_move_speed));
           moveRight = false;
           moveWidth = moveWidthLimit;
       }
       else {
           moveRight = right;  // 충돌했을 경우,
       }
    }

    public void setJumpTwice(boolean infinite) {

    }

    public void death() {
        MainGame.getInstance().remove(this);
        MainGame.getInstance().init();  // 다시 시작.
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
