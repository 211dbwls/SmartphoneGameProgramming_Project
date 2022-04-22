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
import com.example.jellyking.game.block.BlockElectric;
import com.example.jellyking.game.block.BlockJump;
import com.example.jellyking.game.block.BlockMove;
import com.example.jellyking.game.block.BlockStraight;
import com.example.jellyking.game.enemy.EnemyFix;

import com.example.jellyking.game.StageInfo;
import com.example.jellyking.game.enemy.EnemyMoveLR;
import com.example.jellyking.game.enemy.EnemyMoveUD;
import com.example.jellyking.game.item.ItemJumpInfinite;
import com.example.jellyking.game.item.ItemJumpOne;

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
    private BlockElectric blockElectric;  // blockElectric.
    private BlockJump blockJump;  // blockJump.
    private BlockMove blockMove;  // blockMove.
    private BlockStraight blockStraight;  // blockStraight.

    private EnemyFix enemyFix;  // enemyFix.
    private EnemyMoveLR enemyMoveLR;  // enemyMoveLR.
    private EnemyMoveUD enemyMoveUD;  // enemyMoveUD.

    private ItemJumpOne itemJumpOne;  // itemJumpOne.
    private ItemJumpInfinite itemJumpInfinite;  // itemJumpInfinite.

    private Star star;  // star.

    public float frameTime;

    private Paint collisionPaint;

    public static void clear() {
        singleton = null;
    }

    public void init() {
        gameObjects.clear();

        /* Stage */
        float stageX, stageY;
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 22; j++) {
                switch (stage.stage3Info[i][j]) {
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
                    case 23:  // ElectricBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blockElectric = new BlockElectric(stageX, stageY);
                        gameObjects.add(blockElectric);
                        break;
                    case 24:  // JumpBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blockJump = new BlockJump(stageX, stageY);
                        gameObjects.add(blockJump);
                        break;
                    case 25:  // MoveBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blockMove = new BlockMove(stageX, stageY);
                        gameObjects.add(blockMove);
                        break;
                    case 26:  // StraightBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blockStraight = new BlockStraight(stageX, stageY);
                        gameObjects.add(blockStraight);
                        break;
                    case 31:  // FixEnemy
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        enemyFix = new EnemyFix(stageX, stageY);
                        gameObjects.add(enemyFix);
                        break;
                    case 32:  // enemyMoveLR
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        enemyMoveLR = new EnemyMoveLR(stageX, stageY);
                        gameObjects.add(enemyMoveLR);
                        break;
                    case 33:  // enemyMoveUD
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        enemyMoveUD = new EnemyMoveUD(stageX, stageY);
                        gameObjects.add(enemyMoveUD);
                        break;
                    case 41:  // itemJumpOne
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        itemJumpOne = new ItemJumpOne(stageX, stageY);
                        gameObjects.add(itemJumpOne);
                        break;
                    case 42:  // itemJumpInfinite
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        itemJumpInfinite = new ItemJumpInfinite(stageX, stageY);
                        gameObjects.add(itemJumpInfinite);
                        break;
                    case 51:  // Star
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        star = new Star(stageX, stageY);
                        gameObjects.add(star);
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
