package com.example.jellyking.game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import com.example.jellyking.R;
import com.example.jellyking.framework.Metrics;
import com.example.jellyking.framework.Sprite;

public class JellyKing extends Sprite {
    private static final String TAG = JellyKing.class.getSimpleName();

    private float dx, dy;
    private float tx, ty;

    private float jumpHeight = 0.f;
    private float jumpHeightLimit = 70.0f;
    boolean jumpUp = false;

    private float moveWidth = 0.f;
    private float moveWidthLimit = 50.0f;
    boolean move = false;
    boolean moveRight;

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

        if(move == false) {  // 터치하지 않았을 경우
            dx = 0;  // 이동하지 않음
        }
        else {
            if(moveRight == true) {  // 오른쪽으로 이동하는 경우
                if (moveWidth > moveWidthLimit) {   // 이동 거리를 도달했을 경우
                    move = false;  // 이동 멈춤.
                }
            }
            else {  // 왼쪽으로 이동하는 경우
                if(moveWidth < 0) {  // 이동 거리를 도달했을 경우
                    move = false;
                }
            }
        }

        dstRect.offset(dx, dy);
        jumpHeight += dy;
        moveWidth += dx;
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
            moveWidth = 30.f;
        }
    }
}
