package com.example.jellyking.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.nfc.Tag;
import android.util.Log;
import android.view.MotionEvent;

import com.example.jellyking.framework.BoxCollidable;
import com.example.jellyking.framework.GameObject;
import com.example.jellyking.framework.GameView;
import com.example.jellyking.framework.Metrics;
import com.example.jellyking.game.block.Block;
import com.example.jellyking.game.block.BlockBroken;
import com.example.jellyking.game.block.BlockJump;
import com.example.jellyking.game.enemy.EnemyFix;

import com.example.jellyking.game.StageInfo;

import java.util.ArrayList;

public class MainGame {
    private static final String TAG = MainGame.class.getSimpleName();

    private static MainGame singleton;
    public static MainGame getInstance() {
        if (singleton == null) {
            singleton = new MainGame();
        }
        return singleton;
    }

    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    /* gameObjects */
    private JellyKing jellyKing;  // player.

    StageInfo stage = new StageInfo();

    private Block block;  // block.

    private BlockBroken blockBroken;  // blockBroken.

    private BlockJump blockJump;  // blockJump.

    private EnemyFix enemyFix;  // enemyFix.

    public float frameTime;

    private Paint collisionPaint;

    public static void clear() {
        singleton = null;
    }

    public void init() {
        gameObjects.clear();

        /*
        10 : 아무것도 없음.
        21 : Block | 22 : BrokenBlock | 23 : ElectricBlock | 24 : JumpBlock | 25 : MoveBlock | 26 : StraightBlock
        31 : FixEnemy | 32 : MoveLREnemy | 33 : MoveUDEnemy
        41 :JumpOneItem | 42 : JumpInfiniteItem
        51 : Coin
        61 : StartPoint
         */
        float stageX, stageY;
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 22; j++) {
                switch (stage.stage1Info[i][j]) {
                    case 21:  // Block
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        block = new Block(stageX, stageY);
                        gameObjects.add(block);
                        break;
                    case 22:  // BrokenBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blockBroken = new BlockBroken(stageX, stageY);
                        gameObjects.add(blockBroken);
                        break;
                    case 24:  // JumpBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blockJump = new BlockJump(stageX, stageY);
                        gameObjects.add(blockJump);
                        break;
                    case 31:  // FixEnemy
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        enemyFix = new EnemyFix(stageX, stageY);
                        gameObjects.add(enemyFix);
                        break;
                    case 61:  // StartPoint
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        jellyKing = new JellyKing(stageX, stageY);
                        gameObjects.add(jellyKing);
                        break;
                    default:
                        break;
                }
            }
        }

        /* 충돌 상자 */
        collisionPaint = new Paint();
        collisionPaint.setStyle(Paint.Style.STROKE);
        collisionPaint.setColor(Color.RED);
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                // fighter.setTargetPosition(x, y);
                return true;
        }
        return false;
    }

    public void draw(Canvas canvas) {
        for (GameObject gobj : gameObjects) {
            gobj.draw(canvas);

            if(gobj instanceof BoxCollidable) {  // 바운딩 박스 그리기.
                RectF box = ((BoxCollidable) gobj).getBoundingRect();
                canvas.drawRect(box, collisionPaint);
            }
        }
    }

    public void update(int elapsedNanos) {
        frameTime = (float) (elapsedNanos / 1_000_000_000f);
        for (GameObject gobj : gameObjects) {
            gobj.update();
        }

        checkCollision();
    }

    private void checkCollision() {

    }

    public void add(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                gameObjects.add(gameObject);
            }
        });
    }

    public void remove(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                gameObjects.remove(gameObject);
            }
        });
    }

    public int objectCount() {
        return gameObjects.size();
    }
}
