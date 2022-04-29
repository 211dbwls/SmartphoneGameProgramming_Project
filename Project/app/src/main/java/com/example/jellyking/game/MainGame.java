package com.example.jellyking.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import com.example.jellyking.framework.BoxCollidable;
import com.example.jellyking.framework.CollisionHelper;
import com.example.jellyking.framework.GameObject;
import com.example.jellyking.framework.GameView;
import com.example.jellyking.framework.Metrics;
import com.example.jellyking.game.block.Block;
import com.example.jellyking.game.block.BlockBroken;
import com.example.jellyking.game.block.BlockElectric;
import com.example.jellyking.game.block.BlockJump;
import com.example.jellyking.game.block.BlockMoveLR;
import com.example.jellyking.game.block.BlockMoveUD;
import com.example.jellyking.game.block.BlockStraightLeft;
import com.example.jellyking.game.block.BlockStraightRight;
import com.example.jellyking.game.enemy.EnemyDrop;
import com.example.jellyking.game.enemy.EnemyFix;

import com.example.jellyking.game.enemy.EnemyMove;
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

    private Block block;  // block.
    private BlockBroken blockBroken;  // blockBroken.
    private BlockElectric blockElectric;  // blockElectric.
    private BlockJump blockJump;  // blockJump.
    private BlockMoveLR blockMoveLR;  // blockMoveLR.
    private BlockMoveUD blockMoveUD;  // blockMoveUD.
    private BlockStraightRight blockStraightRight;  // blockStraightRight.
    private BlockStraightLeft blockStraightLeft;  // blockStraightLeft.

    private EnemyFix enemyFix;  // enemyFix.
    private EnemyDrop enemyDrop;  // enemyDrop.
    private EnemyMove enemyMove;  // enemyMove.

    private ItemJumpOne itemJumpOne;  // itemJumpOne.
    private ItemJumpInfinite itemJumpInfinite;  // itemJumpInfinite.

    private Star star;  // star.

    StageInfo stage = new StageInfo();

    public float frameTime;

    /* CollisionPaint */
    private Paint collisionPaint;

    public static void clear() {
        singleton = null;
    }

    public void init() {
        gameObjects.clear();

        /* Player */
        float x = stage.stage1StartPointX;
        float y = stage.stage1StartPointY;
        jellyKing = new JellyKing(x, y);
        gameObjects.add(jellyKing);

        /* Stage */
        int[][] stageNum = stage.stage1Info;
        setStage(stageNum);

        /* CollisionPaint */
        collisionPaint = new Paint();
        collisionPaint.setStyle(Paint.Style.STROKE);
        collisionPaint.setColor(Color.RED);
    }

    public void setStage(int[][] stageNum) {
        float stageX, stageY;
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 22; j++) {
                switch (stageNum[i][j]) {
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
                    case 25:  // StraightLeftBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blockStraightLeft = new BlockStraightLeft(stageX, stageY);
                        gameObjects.add(blockStraightLeft);
                        break;
                    case 26:  // StraightRightBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blockStraightRight = new BlockStraightRight(stageX, stageY);
                        gameObjects.add(blockStraightRight);
                        break;
                    case 27:  // MoveLRBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blockMoveLR = new BlockMoveLR(stageX, stageY);
                        gameObjects.add(blockMoveLR);
                        break;
                    case 28:  // MoveUDBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blockMoveUD = new BlockMoveUD(stageX, stageY);
                        gameObjects.add(blockMoveUD);
                        break;
                    case 31:  // FixEnemy
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i) + 18;
                        enemyFix = new EnemyFix(stageX, stageY);
                        gameObjects.add(enemyFix);
                        break;
                    case 32:  // DropEnemy
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        enemyDrop = new EnemyDrop(stageX, stageY);
                        gameObjects.add(enemyDrop);
                        break;
                    case 33:  // MoveEnemy
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        enemyMove = new EnemyMove(stageX, stageY);
                        gameObjects.add(enemyMove);
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
                    default:
                        break;
                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                jellyKing.touch = true;

                float x = event.getX();
                if(x > Metrics.width / 2) {  // 오른쪽 터치
                    jellyKing.setMoveDirection(true);
                }
                else {  // 왼쪽 터치
                    jellyKing.setMoveDirection(false);
                }
                return true;
            case MotionEvent.ACTION_UP:
                jellyKing.touch = false;
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
        for(GameObject o1 : gameObjects) {
            if(!(o1 instanceof JellyKing)) {  // JellyKing이 아닌 경우 무시.
                continue;
            }
            JellyKing jellyKing = (JellyKing) o1;

            for(GameObject o2 : gameObjects) {
                /* Block */
                if(o2 instanceof Block) {  // Block인 경우
                    Block block = (Block) o2;
                    if (CollisionHelper.collides(block, jellyKing)) {  // 충돌했을 경우
                        Log.d(TAG, "Collision : Block");
                        jellyKing.jumpUp = true;   // 계속 점프하도록
                        break;
                    }
                }
                else if(o2 instanceof BlockBroken) {  // BrokenBlock인 경우
                    BlockBroken brokenBlock = (BlockBroken) o2;
                    if(CollisionHelper.collides(brokenBlock, jellyKing)) {  // 충돌했을 경우
                        Log.d(TAG, "Collision : BrokenBlock");
                        remove(brokenBlock);
                        break;
                    }
                }
                else if(o2 instanceof BlockElectric) {  // ElectricBlock인 경우
                    BlockElectric electricBlock = (BlockElectric) o2;
                    if (CollisionHelper.collides(electricBlock, jellyKing)) {  // 충돌했을 경우
                        Log.d(TAG, "Collision : ElectricBlock");
                        // remove(jellyKing);
                        // jellyKing Death
                        break;
                    }
                }
                else if(o2 instanceof BlockJump) {  // JumpBlock인 경우
                    BlockJump jumpBlock = (BlockJump) o2;
                    if (CollisionHelper.collides(jumpBlock, jellyKing)) {  // 충돌했을 경우
                        Log.d(TAG, "Collision : JumpBlock");
                        break;
                    }
                }
                else if(o2 instanceof BlockStraightLeft) {  // StraightLeftBlock인 경우
                    BlockStraightLeft straightLeftBlock = (BlockStraightLeft) o2;
                    if (CollisionHelper.collides(straightLeftBlock, jellyKing)) {  // 충돌했을 경우
                        Log.d(TAG, "Collision : StraightLeftBlock");
                        break;
                    }
                }
                else if(o2 instanceof BlockStraightRight) {  // StraightRightBlock인 경우
                    BlockStraightRight straightRightBlock = (BlockStraightRight) o2;
                    if (CollisionHelper.collides(straightRightBlock, jellyKing)) {  // 충돌했을 경우
                        Log.d(TAG, "Collision : StraightRightBlock");
                        break;
                    }
                }
                else if(o2 instanceof BlockMoveLR) {  // MoveLRBlock인 경우
                    BlockMoveLR moveLRBlock = (BlockMoveLR) o2;
                    if (CollisionHelper.collides(moveLRBlock, jellyKing)) {  // 충돌했을 경우
                        Log.d(TAG, "Collision : MoveLRBlock");
                        break;
                    }
                }
                else if(o2 instanceof BlockMoveUD) {  // MoveUDBlock인 경우
                    BlockMoveUD moveUDBlock = (BlockMoveUD) o2;
                    if (CollisionHelper.collides(moveUDBlock, jellyKing)) {  // 충돌했을 경우
                        Log.d(TAG, "Collision : MoveUDBlock");
                        break;
                    }
                }
                /* Enemy */
                else if(o2 instanceof EnemyFix) {  // FixEnemy인 경우
                    EnemyFix fixEnemy = (EnemyFix) o2;
                    if (CollisionHelper.collides(fixEnemy, jellyKing)) {  // 충돌했을 경우
                        Log.d(TAG, "Collision : FixEnemy");
                        break;
                    }
                }
                else if(o2 instanceof EnemyDrop) {  // DropEnemy인 경우
                    EnemyDrop dropEnemy = (EnemyDrop) o2;
                    if (CollisionHelper.collides(dropEnemy, jellyKing)) {  // 충돌했을 경우
                        Log.d(TAG, "Collision : DropEnemy");
                        break;
                    }
                }
                else if(o2 instanceof EnemyMove) {  // MoveEnemy인 경우
                    EnemyMove moveEnemy = (EnemyMove) o2;
                    if (CollisionHelper.collides(moveEnemy, jellyKing)) {  // 충돌했을 경우
                        Log.d(TAG, "Collision : MoveEnemy");
                        break;
                    }
                }
                /* Item */
                else if(o2 instanceof ItemJumpOne) {  // JumpOneItem인 경우
                    ItemJumpOne jumpOneItem = (ItemJumpOne) o2;
                    if (CollisionHelper.collides(jumpOneItem, jellyKing)) {  // 충돌했을 경우
                        Log.d(TAG, "Collision : JumpOneItem");
                        break;
                    }
                }
                else if(o2 instanceof ItemJumpInfinite) {  // JumpInfiniteItem인 경우
                    ItemJumpInfinite jumpInfiniteItem = (ItemJumpInfinite) o2;
                    if (CollisionHelper.collides(jumpInfiniteItem, jellyKing)) {  // 충돌했을 경우
                        Log.d(TAG, "Collision : JumpInfiniteItem");
                        break;
                    }
                }
                /* Star */
                else if(o2 instanceof Star) {  // Star인 경우
                    Star star = (Star) o2;
                    if (CollisionHelper.collides(star, jellyKing)) {  // 충돌했을 경우
                        Log.d(TAG, "Collision : Star");
                        break;
                    }
                }
            }
        }
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
