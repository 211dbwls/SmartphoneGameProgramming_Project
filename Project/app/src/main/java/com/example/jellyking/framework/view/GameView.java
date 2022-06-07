package com.example.jellyking.framework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.jellyking.framework.res.Metrics;
import com.example.jellyking.game.MainScene;

public class GameView extends View implements Choreographer.FrameCallback {
    public static GameView view;

    private static final String TAG = GameView.class.getSimpleName();

    private Paint fpsPaint = new Paint();

    private long lastTimeNanos;
    private int framesPerSecond;
    private boolean initialized;

    private boolean running;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        view = this;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  // 사이즈 변경.
        super.onSizeChanged(w, h, oldw, oldh);

        Metrics.width = w;
        Metrics.height = h;

        if (!initialized) {  // initialized == false일 경우
            initView();  // 초기화.
            initialized = true;
            running = true;
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    @Override
    public void doFrame(long currentTimeNanos) {
        if(!running){  // running == false일 경우.
            Log.d(TAG, "Running is false on doFrame()");
            return;  // 게임 실행하지 않음
        }

        long now = currentTimeNanos;  // 현재 시간 저장.
        if(lastTimeNanos == 0) {
            lastTimeNanos = now;
        }

        int elapsed = (int) (now - lastTimeNanos);  // 흐른 시간.
        if (elapsed != 0) {  // 흐른 시간이 있는 경우.
            framesPerSecond = 1_000_000_000 / elapsed;
            lastTimeNanos = now;  // 지난 시간 저장.
            MainScene game = MainScene.get();
            game.update(elapsed);
            invalidate();
        }

        Choreographer.getInstance().postFrameCallback(this);
    }

    private void initView() {
        MainScene.get().init();

        // fpsPaint.setColor(Color.BLUE);
        // fpsPaint.setTextSize(100);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return MainScene.get().onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        MainScene.get().draw(canvas);

        // canvas.drawText("FPS:" + framesPerSecond, framesPerSecond * 10, 100, fpsPaint);
        // canvas.drawText("" + MainGame.getInstance().objectCount(), 10, 100, fpsPaint);
    }

    public void pauseGame() {  // 게임 멈추는 함수.
        running = false;
    }

    public void resumeGame() {  // 게임 다시 시작하는 함수.
        if(initialized && !running) {
            running = true;
            lastTimeNanos = 0;

            Choreographer.getInstance().postFrameCallback(this);

            Log.d(TAG, "Resuming game");
        }
    }
}
