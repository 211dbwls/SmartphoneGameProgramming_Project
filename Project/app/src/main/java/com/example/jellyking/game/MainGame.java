package com.example.jellyking.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.example.jellyking.R;
import com.example.jellyking.framework.BoxCollidable;
import com.example.jellyking.framework.GameObject;
import com.example.jellyking.framework.GameView;
import com.example.jellyking.framework.Metrics;
import com.example.jellyking.game.block.Block;
import com.example.jellyking.game.block.BlockBroken;
import com.example.jellyking.game.block.BlockJump;
import com.example.jellyking.game.enemy.EnemyFix;

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

    private Block block;  // block.
    private float blockX, blockY;

    private BlockBroken blockBroken;  // blockBroken.
    private float blockBrokenX, blockBrokenY;

    private BlockJump blockJump;  // blockJump.
    private float blockJumpX, blockJumpY;

    private EnemyFix enemyFix;  // enemyFix.
    private float enemyFixX, enemyFixY;

    public float frameTime;

    private Paint collisionPaint;

    public static void clear() {
        singleton = null;
    }

    public void init() {
        gameObjects.clear();

        /* player(jellyKing) 추가 */
        float fx = Metrics.width / 2;
        float fy = Metrics.height / 2;
        jellyKing = new JellyKing(fx, fy);
        gameObjects.add(jellyKing);

        /* 블록 - 기본 */
        for(int i = 0; i < 10; i++) {
            blockX = Metrics.width / 26 * 2;  // x : 1
            blockY = Metrics.height / 13 * 3 + (Metrics.height / 13 * (9 - i));
            block = new Block(blockX, blockY);
            gameObjects.add(block);
        }

        for(int i = 0; i < 4; i++) {
            for(int j = 1; j <= 2; j++) {  // x : 1, 2
                blockX = Metrics.width / 26 * (2 + j) ;
                blockY = Metrics.height / 13 * 3  + (Metrics.height / 13 * (9 - i));
                block = new Block(blockX, blockY);
                gameObjects.add(block);
            }
        }

        for(int i = 0; i < 3; i++) {
            for(int j = 5; j <= 6; j++) {  // x : 5, 6
                blockX = Metrics.width / 26 * (2 + j) ;
                blockY = Metrics.height / 13 * 3  + (Metrics.height / 13 * (9 - i));
                block = new Block(blockX, blockY);
                gameObjects.add(block);
            }
        }

        blockX = Metrics.width / 26 * (2 + 9) ;  // x : 9
        blockY = Metrics.height / 13 * 3  + (Metrics.height / 13 * 9);
        block = new Block(blockX, blockY);
        gameObjects.add(block);

        for(int j = 10; j <= 19; j++) {
            blockX = Metrics.width / 26 * (2 + j);  //x : 10 - 19
            blockY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 6);  // y : 6
            block = new Block(blockX, blockY);
            gameObjects.add(block);
        }

        for(int i = 0; i < 6; i++) {
            blockX = Metrics.width / 26 * (2 + 10);  // x : 10
            blockY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
            block = new Block(blockX, blockY);
            gameObjects.add(block);
        }

        blockX = Metrics.width / 26 * (2 + 14) ;  // 14
        blockY = Metrics.height / 13 * 3  + (Metrics.height / 13 * 9);
        block = new Block(blockX, blockY);
        gameObjects.add(block);

        for(int j = 19; j <= 21; j++) {  //x : 19, 20, 21, 21
            blockX = Metrics.width / 26 * (2 + j);
            blockY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 9);
            block = new Block(blockX, blockY);
            gameObjects.add(block);
        }

        for(int j = 18; j <= 21; j++) {
            blockX = Metrics.width / 26 * (2 + j);  //x : 18 - 21
            blockY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 3);  // y : 3
            block = new Block(blockX, blockY);
            gameObjects.add(block);
        }

        for(int i = 0; i < 10; i++) {
            blockX = Metrics.width / 26 * (2 + 22);  // x : 22
            blockY = Metrics.height / 13 * 3 + (Metrics.height / 13 * (9 - i));
            block = new Block(blockX, blockY);
            gameObjects.add(block);
        }

        /* 블록 - 부서지는 */
        blockBrokenX = Metrics.width / 26 * (2 + 3);  // x : 3
        blockBrokenY = Metrics.height / 13 * 3 + (Metrics.height / 13 * (9 - 2));
        blockBroken = new BlockBroken(blockBrokenX, blockBrokenY);
        gameObjects.add(blockBroken);

        blockBrokenX = Metrics.width / 26 * (2 + 7);  // x : 7
        blockBrokenY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 9);
        blockBroken = new BlockBroken(blockBrokenX, blockBrokenY);
        gameObjects.add(blockBroken);

        for(int j = 10; j <= 13; j++) {
            blockBrokenX = Metrics.width / 26 * (2 + j);  //x : 10 - 13
            blockBrokenY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 9);
            blockBroken = new BlockBroken(blockBrokenX, blockBrokenY);
            gameObjects.add(blockBroken);
        }

        blockBrokenX = Metrics.width / 26 * (2 + 16);  // x : 16
        blockBrokenY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 3);
        blockBroken = new BlockBroken(blockBrokenX, blockBrokenY);
        gameObjects.add(blockBroken);

        /* 블록 - 점프 */
        for(int j = 1; j <= 2; j++) {
            blockJumpX = Metrics.width / 26 * (2 + j);  //x : 1, 2
            blockJumpY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 5);
            blockJump = new BlockJump(blockJumpX, blockJumpY);
            gameObjects.add(blockJump);
        }

        blockJumpX = Metrics.width / 26 * (2 + 4);  //x : 4
        blockJumpY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 7);
        blockJump = new BlockJump(blockJumpX, blockJumpY);
        gameObjects.add(blockJump);

        blockJumpX = Metrics.width / 26 * (2 + 8);  //x : 8
        blockJumpY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 9);
        blockJump = new BlockJump(blockJumpX, blockJumpY);
        gameObjects.add(blockJump);

        /* 아이템 */

        /* 적 */
        for(int j = 3; j <= 4; j++) {
            enemyFixX = Metrics.width / 26 * (2 + j);  //x : 3, 4
            enemyFixY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 9);
            enemyFix = new EnemyFix(enemyFixX, enemyFixY);
            gameObjects.add(enemyFix);
        }

        enemyFixX = Metrics.width / 26 * (2 + 17);  //x : 17
        enemyFixY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 3);
        enemyFix = new EnemyFix(enemyFixX, enemyFixY);
        gameObjects.add(enemyFix);

        enemyFixX = Metrics.width / 26 * (2 + 18);  //x : 18
        enemyFixY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 9);
        enemyFix = new EnemyFix(enemyFixX, enemyFixY);
        gameObjects.add(enemyFix);

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
