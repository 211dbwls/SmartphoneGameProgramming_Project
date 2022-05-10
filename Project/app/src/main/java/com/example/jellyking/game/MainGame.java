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

    protected ArrayList<ArrayList<GameObject>> layers;
    public enum Layer {
        bg, object, player, controller, COUNT
    }

    StageInfo stage;
    public int stageNum;

    /* gameObjects */
    private JellyKing jellyKing;
    private Blocks blocks;
    private Enemies enemies;
    private Items items;
    private Stars stars;

    private int maxStarCount;
    private int starCount;

    public float frameTime;

    /* CollisionPaint */
    private Paint collisionPaint;

    public static void clear() {
        singleton = null;
    }

    public void init() {
        initLayers(Layer.COUNT.ordinal());

        /* Stage */
        stage = new StageInfo(stageNum);

        int[][] stageInfo;
        switch (stageNum) {
            case 1:
                stageInfo = stage.stage1Info;
                setStage(stageInfo);
                break;
            case 2:
                stageInfo = stage.stage2Info;
                setStage(stageInfo);
                break;
            case 3:
                stageInfo = stage.stage3Info;
                setStage(stageInfo);
                break;
            case 4:
                stageInfo = stage.stage4Info;
                setStage(stageInfo);
                break;
            case 5:
                stageInfo = stage.stage5Info;
                setStage(stageInfo);
                break;
        }

        /* Player */
        float x = stage.startX;
        float y = stage.startY;
        jellyKing = new JellyKing(x, y);
        //float testX = Metrics.width / 26 * (3 + 2);
        //float testY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 0);
        //jellyKing = new JellyKing(testX, testY);
        add(Layer.player, jellyKing);

        /* add */
        add(Layer.controller, new CollisionChecker());

        /* 모아야 할 별 개수 */
        maxStarCount = stage.maxStar;  // 모아야 할 별 개수
        starCount = 0;  // 모은 별 개수

        /* CollisionPaint */
        collisionPaint = new Paint();
        collisionPaint.setStyle(Paint.Style.STROKE);
        collisionPaint.setColor(Color.RED);
    }

    public void setStage(int[][] stageInfo) {
        float stageX, stageY;
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 22; j++) {
                switch (stageInfo[i][j]) {
                    case 21:  // Block
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blocks = new Blocks(stageX, stageY, 0, 0, 0, 1);
                        add(Layer.object, blocks);
                        break;
                    case 22:  // BrokenBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blocks = new Blocks(stageX, stageY, 0, 0, 1, 2);
                        add(Layer.object, blocks);
                        break;
                    case 23:  // ElectricBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blocks = new Blocks(stageX, stageY, 0, 0, 2, 3);
                        add(Layer.object, blocks);
                        break;
                    case 24:  // JumpBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blocks = new Blocks(stageX, stageY, 0, 1, 4, 4);
                        add(Layer.object, blocks);
                        break;
                    case 25:  // StraightLeftBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blocks = new Blocks(stageX, stageY, 0, 0, 6, 7);
                        add(Layer.object, blocks);
                        break;
                    case 26:  // StraightRightBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blocks = new Blocks(stageX, stageY, 0, 0, 7, 8);
                        add(Layer.object, blocks);
                        break;
                    case 27:  // MoveLRBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blocks = new Blocks(stageX, stageY, 2, 3, 5, 5);
                        add(Layer.object, blocks);
                        break;
                    case 28:  // MoveUDBlock
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        blocks = new Blocks(stageX, stageY, 0, 0, 0, 6);
                        add(Layer.object, blocks);
                        break;
                    case 31:  // FixEnemy
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i) + 18;
                        enemies = new Enemies(stageX, stageY, 1, 2, 0, 1);
                        add(Layer.object, enemies);
                        break;
                    case 32:  // DropEnemy
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        enemies = new Enemies(stageX, stageY, 0, 0, 2, 2);
                        add(Layer.object, enemies);
                        break;
                    case 33:  // MoveEnemy
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        enemies = new Enemies(stageX, stageY, 0, 0, 5, 3);
                        add(Layer.object, enemies);
                        break;
                    case 34:  // MoveLREnemy
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        enemies = new Enemies(stageX, stageY, 0, 0, 7, 4);
                        add(Layer.object, enemies);
                        break;
                    case 41:  // itemJumpOne
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        items = new Items(stageX, stageY, 0, 1);
                        add(Layer.object, items);
                        break;
                    case 42:  // itemJumpInfinite
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        items = new Items(stageX, stageY, 1, 2);
                        add(Layer.object, items);
                        break;
                    case 51:  // Star
                        stageX = Metrics.width / 26 * (3 + j);
                        stageY = Metrics.height / 13 * 3 + (Metrics.height / 13 * i);
                        stars = new Stars(stageX, stageY);
                        add(Layer.object, stars);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void initLayers(int count) {  // layer 초기화
        layers = new ArrayList<>();
        for(int i = 0;i < count;i++) {
            layers.add(new ArrayList<>());
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                jellyKing.touch = true;

                float x = event.getX();
                if(x > Metrics.width / 2) {  // 오른쪽 터치
                    jellyKing.setMoveDirection(true, false);
                }
                else {  // 왼쪽 터치
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
        for (ArrayList<GameObject> gameObjects : layers) {
            for (GameObject gobj : gameObjects) {
                gobj.draw(canvas);

                if (gobj instanceof BoxCollidable) {  // 바운딩 박스 그리기.
                    RectF box = ((BoxCollidable) gobj).getBoundingRect();
                    RectF boxHead = ((BoxCollidable) gobj).getBoundingRectHead();
                    RectF boxFoot = ((BoxCollidable) gobj).getBoundingRectFoot();
                    RectF boxLeft = ((BoxCollidable) gobj).getBoundingRectLeft();
                    RectF boxRight = ((BoxCollidable) gobj).getBoundingRectRight();

                    if (box != null) {
                        canvas.drawRect(box, collisionPaint);
                    }
                    if (boxHead != null) {
                        canvas.drawRect(boxHead, collisionPaint);
                    }
                    if (boxFoot != null) {
                        canvas.drawRect(boxFoot, collisionPaint);
                    }
                    if (boxLeft != null) {
                        canvas.drawRect(boxLeft, collisionPaint);
                    }
                    if (boxRight != null) {
                        canvas.drawRect(boxRight, collisionPaint);
                    }
                }
            }
        }
    }

    public void update(int elapsedNanos) {
        frameTime = (float) (elapsedNanos / 1_000_000_000f);
        for (ArrayList<GameObject> gameObjects : layers) {
            for(GameObject gobj : gameObjects) {
                gobj.update();
            }
        }
    }

    public ArrayList<GameObject> objectsAt(Layer layer) {
        return layers.get(layer.ordinal());
    }

    public void add(Layer layer, GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> gameObjects = layers.get(layer.ordinal());
                gameObjects.add(gameObject);
            }
        });
    }

    public void remove(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                for(ArrayList<GameObject> gameObjects : layers) {
                    boolean removed = gameObjects.remove(gameObject);

                    if(!removed) {
                        continue;
                    }

                    break;
                }
            }
        });
    }

    public int objectCount() {
        int count = 0;
        for(ArrayList<GameObject> gameObjects : layers) {
            count += gameObjects.size();
        }
        return count;
    }
}
