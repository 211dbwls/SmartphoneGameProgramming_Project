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
    private float jumpHeightLimit = 30.0f;

    boolean jumpUp = false;

    public JellyKing(float x, float y) {
        super(x, y, R.dimen.jellyking_radius, R.mipmap.jellyking_pink);  // jellyKing 생성
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;

        dy = Metrics.size(R.dimen.jellyking_jump_speed);

        float dy = this.dy * frameTime;

        Log.d(TAG, String.valueOf(jumpHeight));
        if(jumpUp == false) {  // 아래로 이동 중인 경우
            if(jumpHeight > jumpHeightLimit) {  // 아래에 닿았을 경우
                jumpUp = true;  // 위로 이동하도록
            }
            dstRect.offset(0, dy);
            jumpHeight += dy;
        }
        else {  // 위로 이동 중인 경우
            if(jumpHeight < 0) {  // 위에 닿았을 경우
                jumpUp = false;  // 아래로 이동하도록
            }
            dstRect.offset(0, -dy);
            jumpHeight -= dy;
        }
    }
}
