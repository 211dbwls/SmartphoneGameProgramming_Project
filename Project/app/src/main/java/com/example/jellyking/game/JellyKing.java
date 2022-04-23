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

    private static final float MOVE_WIDTH_LIMIT_SHORT = 100.0f;
    private static final float MOVE_WIDTH_LIMIT_LONG = 120.0f;

    private float dx, dy;

    private float jumpHeight = 0.0f;
    private float jumpHeightLimit = 70.0f;
    boolean jumpUp = false;

    private float moveWidth = 0.0f;
    private float moveWidthLimit = MOVE_WIDTH_LIMIT_SHORT;
    boolean move = false;
    boolean moveRight;

    private float touchTime = 0.0f;
    boolean touch = false;

    protected RectF boundingBox = new RectF();  // boundingBox

    public JellyKing(float x, float y) {
        super(x, y, R.dimen.jellyking_radius, R.mipmap.jellyking_pink);  // jellyKing 생성
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;

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

        /* 계속 점프 */
        if(jumpUp == false) {  // 아래로 이동 중인 경우
            if (jumpHeight > jumpHeightLimit) {  // 아래에 닿았을 경우
                jumpUp = true;  // 위로 이동하도록
            }
            if(dy < 0) {
                dy = -dy;
            }
        }
        else {  // 위로 이동 중인 경우
            if (jumpHeight < 0) {  // 위에 닿았을 경우
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
                    }
                } else {  // 왼쪽으로 이동하는 경우
                    if (moveWidth < 0) {  // 이동 거리를 도달했을 경우
                        move = false;
                    }
                }
            }
        }
        else {  // 터치하지 않았을 경우
            dx = 0;  // 이동하지 않음
        }

        dstRect.offset(dx, dy);
        x += dx;
        y += dy;
        jumpHeight += dy;
        moveWidth += dx;

        /* boundingBox */
        float widthRadius = Metrics.size(R.dimen.jellyking_radius);
        boundingBox.set(x - widthRadius, y - widthRadius, x + widthRadius, y + widthRadius);
    }

    public void setMoveDirection(boolean right) {
        move = true;

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
}
