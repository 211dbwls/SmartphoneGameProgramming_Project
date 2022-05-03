package com.example.jellyking.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import com.example.jellyking.framework.BoxCollidable;
import com.example.jellyking.framework.CollisionHelper;
import com.example.jellyking.framework.GameObject;
import com.example.jellyking.framework.GameView;
import com.example.jellyking.framework.Metrics;

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

    /*private Block block;  // block.
    private BlockBroken blockBroken;  // blockBroken.
    private BlockElectric blockElectric;  // blockElectric.
    private BlockJump blockJump;  // blockJump.
    private BlockMoveLR blockMoveLR;  // blockMoveLR.
    private BlockMoveUD blockMoveUD;  // blockMoveUD.
    private BlockStraightRight blockStraightRight;  // blockStraightRight.
    private BlockStraightLeft blockStraightLeft;  // blockStraightLeft.*/

    private Blocks blocks;
    private Enemies enemies;
    private Items items;
    private Star star;

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
        float x = stage.stage2StartPointX;
        float y = stage.stage2StartPointY;
        jellyKing = new JellyKing(x, y);
        gameObjects.add(jellyKing);

        /* Stage */
        int[][] stageNum = stage.stage5Info;
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
                        blocks = new Blocks(stageX, stageY, 0, 0, 0, 1);
                        gameObjects.add(blocks);
                        break;
                    case 22:  // BrokenBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blocks = new Blocks(stageX, stageY, 0, 0, 1, 2);
                        gameObjects.add(blocks);
                        break;
                    case 23:  // ElectricBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blocks = new Blocks(stageX, stageY, 0, 0, 2, 3);
                        gameObjects.add(blocks);
                        break;
                    case 24:  // JumpBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blocks = new Blocks(stageX, stageY, 0, 1, 4, 4);
                        gameObjects.add(blocks);
                        break;
                    case 25:  // StraightLeftBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blocks = new Blocks(stageX, stageY, 0, 0, 6, 7);
                        gameObjects.add(blocks);
                        break;
                    case 26:  // StraightRightBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blocks = new Blocks(stageX, stageY, 0, 0, 7, 8);
                        gameObjects.add(blocks);
                        break;
                    case 27:  // MoveLRBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blocks = new Blocks(stageX, stageY, 2, 3, 5, 5);
                        gameObjects.add(blocks);
                        break;
                    case 28:  // MoveUDBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blocks = new Blocks(stageX, stageY, 0, 0, 0, 6);
                        gameObjects.add(blocks);
                        break;
                    case 31:  // FixEnemy
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i) + 18;
                        enemies = new Enemies(stageX, stageY, 1, 2, 0, 1);
                        gameObjects.add(enemies);
                        break;
                    case 32:  // DropEnemy
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        enemies = new Enemies(stageX, stageY, 0, 0, 3, 2);
                        gameObjects.add(enemies);
                        break;
                    case 33:  // MoveEnemy
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        enemies = new Enemies(stageX, stageY, 0, 0, 5, 3);
                        gameObjects.add(enemies);
                        break;
                    case 34:  // MoveLREnemy
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        enemies = new Enemies(stageX, stageY, 0, 0, 6, 4);
                        gameObjects.add(enemies);
                        break;
                    case 41:  // itemJumpOne
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        items = new Items(stageX, stageY, 0, 1);
                        gameObjects.add(items);
                        break;
                    case 42:  // itemJumpInfinite
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        items = new Items(stageX, stageY, 1, 2);
                        gameObjects.add(items);
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
                    jellyKing.collisionStraightLeftBlock = false;
                    jellyKing.setMoveDirection(true, false);
                }
                else {  // 왼쪽 터치
                    jellyKing.collisionStraightLeftBlock = false;
                    jellyKing.setMoveDirection(false, false);
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
                RectF boxHead = ((BoxCollidable) gobj).getBoundingRectHead();
                RectF boxFoot = ((BoxCollidable) gobj).getBoundingRectFoot();
                RectF boxLeft = ((BoxCollidable) gobj).getBoundingRectLeft();
                RectF boxRight = ((BoxCollidable) gobj).getBoundingRectRight();

                if(box != null) {
                    canvas.drawRect(box, collisionPaint);
                }
                if(boxHead != null) {
                    canvas.drawRect(boxHead, collisionPaint);
                }
                if(boxFoot != null) {
                    canvas.drawRect(boxFoot, collisionPaint);
                }
                if(boxLeft != null) {
                    canvas.drawRect(boxLeft, collisionPaint);
                }
                if(boxRight != null) {
                    canvas.drawRect(boxRight, collisionPaint);
                }
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
                if(o2 instanceof Blocks) {  // Block인 경우
                    Blocks block = (Blocks) o2;
                    switch (block.blockType) {
                        case 1:  // Block
                            if(CollisionHelper.collides(block.getBoundingRectFoot(), jellyKing.getBoundingRectHead())) {
                                Log.d(TAG, "Collision : Block(Foot)");
                                jellyKing.jumpUp = false;  // 떨어지도록
                                break;
                            }
                            if(CollisionHelper.collides(block.getBoundingRectLeft(), jellyKing.getBoundingRectRight())) {
                                Log.d(TAG, "Collision : Block(Left)");
                                jellyKing.setMoveDirection(false, true);  // 반대 방향으로 튕기도록
                                break;
                            }
                            if(CollisionHelper.collides(block.getBoundingRectRight(), jellyKing.getBoundingRectLeft())) {
                                Log.d(TAG, "Collision : Block(Right)");
                                jellyKing.setMoveDirection(true, false);  // 반대 방향으로 튕기도록
                                break;
                            }
                            if (CollisionHelper.collides(block.getBoundingRectHead(), jellyKing.getBoundingRectFoot())) {
                                Log.d(TAG, "Collision : Block(Head)");
                                jellyKing.jumpUp = true;   // 점프하도록
                                break;
                            }
                            break;
                        case 2:  // BrokenBlock
                            if(CollisionHelper.collides(block.getBoundingRectFoot(), jellyKing.getBoundingRectHead())) {
                                Log.d(TAG, "Collision : BrokenBlock(Foot)");
                                jellyKing.jumpUp = false;   // 떨어지도록
                                break;
                            }
                            if(CollisionHelper.collides(block.getBoundingRectHead(), jellyKing.getBoundingRectFoot())
                                    || CollisionHelper.collides(block.getBoundingRectHead(), jellyKing.getBoundingRectLeft())
                                    || CollisionHelper.collides(block.getBoundingRectHead(), jellyKing.getBoundingRectRight()) ) {
                                Log.d(TAG, "Collision : BrokenBlock(Head)");
                                jellyKing.jumpUp = true;   // 점프하도록
                                remove(block);
                                break;
                            }
                            break;
                        case 3:  // ElectricBlock
                            if (CollisionHelper.collides(block.getBoundingRect(), jellyKing.getBoundingRect())) {  // 충돌했을 경우
                                Log.d(TAG, "Collision : ElectricBlock");
                                jellyKing.death();
                                break;
                            }
                            break;
                        case 4:  // JumpBlock
                            if (CollisionHelper.collides(block.getBoundingRectHead(), jellyKing.getBoundingRectFoot())) {  // 충돌했을 경우
                                Log.d(TAG, "Collision : JumpBlock");
                                jellyKing.collisionJumpBlock = true;
                                jellyKing.jumpUp = true;   // 점프하도록
                                block.jumpBlockCollision = true;
                                break;
                            }
                            break;
                        case 5:  // MoveLRBlock
                            if (CollisionHelper.collides(block.getBoundingRectHead(), jellyKing.getBoundingRectFoot())) {  // 충돌했을 경우
                                Log.d(TAG, "Collision : MoveLRBlock");
                                jellyKing.jumpUp = true;   // 점프하도록
                                break;
                            }
                            break;
                        case 6:  // MoveUDBlock
                            if (CollisionHelper.collides(block.getBoundingRectHead(), jellyKing.getBoundingRectFoot())) {  // 충돌했을 경우
                                Log.d(TAG, "Collision : MoveUDBlock");
                                jellyKing.jumpUp = true;   // 점프하도록
                                break;
                            }
                            break;
                        case 7:  // StraightLeftBlock
                            if (CollisionHelper.collides(block.getBoundingRectHead(), jellyKing.getBoundingRectFoot())) {  // 충돌했을 경우
                                Log.d(TAG, "Collision : StraightLeftBlock");
                                jellyKing.collisionStraightLeftBlock = true;  // 왼쪽으로 이동하도록
                                jellyKing.collisionStraightLeftBlockY = block.startY;
                                break;
                            }
                            break;
                        case 8:  // StraightRightBlock
                            if (CollisionHelper.collides(block.getBoundingRectHead(), jellyKing.getBoundingRectFoot())) {  // 충돌했을 경우
                                Log.d(TAG, "Collision : StraightRightBlock");
                                jellyKing.collisionStraightRightBlock = true;  // 오른쪽으로 이동하도록
                                jellyKing.collisionStraightRightBlockY = block.startY;
                                break;
                            }
                            break;
                    }
                }
                /* Enemy */
                else if(o2 instanceof Enemies) {  // Enemy인 경우
                    Enemies enemy = (Enemies) o2;
                    switch (enemy.enemyType) {
                        case 1:  // FixEnemy
                        case 2:  // DropEnemy
                        case 3:  // MoveUDEnemy
                            if (CollisionHelper.collides(enemy.getBoundingRect(), jellyKing.getBoundingRect())) {  // 충돌했을 경우
                                Log.d(TAG, "Collision : Enemy(FixEnemy, DropEnemy, MoveUDEnemy)");
                                jellyKing.death();
                                break;
                            }
                            break;
                        case 4:  // MoveLREnemy
                            if (CollisionHelper.collides(enemy.getBoundingRectHead(), jellyKing.getBoundingRectFoot())) {  // 충돌했을 경우
                                Log.d(TAG, "Collision : MoveLREnemy(Head)");
                                enemy.death();
                                break;
                            }
                            if (CollisionHelper.collides(enemy.getBoundingRect(), jellyKing.getBoundingRect())) {  // 충돌했을 경우
                                Log.d(TAG, "Collision : MoveLREnemy");
                                jellyKing.death();
                                break;
                            }
                            break;
                    }
                }
                /* Item */
                /*else if(o2 instanceof Items) {  // JumpOneItem인 경우
                    Items item = (Items) o2;
                    switch (item.itemType) {
                        case 1:  // JumpOneItem
                            if (CollisionHelper.collides(item.getBoundingRect(), jellyKing.getBoundingRect())) {  // 충돌했을 경우
                                Log.d(TAG, "Collision : JumpOneItem");
                                break;
                            }
                            break;
                        case 2:  // JumpInfiniteItem
                            if (CollisionHelper.collides(item.getBoundingRect(), jellyKing.getBoundingRect())) {  // 충돌했을 경우
                                Log.d(TAG, "Collision : JumpInfiniteItem");
                                break;
                            }
                            break;
                    }
                }*/
                /* Star */
                /*else if(o2 instanceof Star) {  // Star인 경우
                    Star star = (Star) o2;
                    if (CollisionHelper.collides(star, jellyKing)) {  // 충돌했을 경우
                        Log.d(TAG, "Collision : Star");
                        break;
                    }
                }*/
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
