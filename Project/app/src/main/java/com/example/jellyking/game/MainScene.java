package com.example.jellyking.game;

import android.util.Log;
import android.view.MotionEvent;

import com.example.jellyking.R;
import com.example.jellyking.app.StageActivity;
import com.example.jellyking.framework.object.Button;
import com.example.jellyking.framework.object.Sprite;
import com.example.jellyking.framework.res.Sound;
import com.example.jellyking.framework.interfaces.GameObject;
import com.example.jellyking.framework.res.Metrics;

import java.util.ArrayList;

public class MainScene extends Scene{
    private static final String TAG = MainScene.class.getSimpleName();

    private static MainScene singleton;
    public static MainScene get() {
        if (singleton == null) {
            singleton = new MainScene();
        }
        return singleton;
    }

    public enum Layer {
        bg, object, player, bgUi, ui, controller, COUNT
    }

    /* stage */
    StageInfo stage;
    public int stageNum;
    public int stageOpenNumS;

    /* gameObjects */
    private JellyKing jellyKing;
    private Blocks blocks;
    private Enemies enemies;
    private Items items;
    private Stars stars;

    public int maxStarCount;

    public float size(float unit) {
        return Metrics.height / 9.5f * unit;
    }

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

        stageOpenNumS = stage.stageOpenNum;

        /* Player */
        float x = stage.startX;
        float y = stage.startY;
        jellyKing = new JellyKing(x, y);
        add(Layer.player, jellyKing);

        /* add */
        add(Layer.controller, new CollisionChecker());
        add(Layer.bg, new Background(R.mipmap.background_colored_land));

        /* 모아야 할 별 개수 */
        maxStarCount = stage.maxStar;  // 모아야 할 별 개수
        jellyKing.starCount = 0;  // 모은 별 개수

        start();
    }

    @Override
    public boolean handleBackKey() {
        push(PausedScene.get());
        return true;
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

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();

                if(y > Metrics.height / 2 - Metrics.height / 4) {
                    jellyKing.touch = true;
                    if (x > Metrics.width / 2) {  // 오른쪽 터치
                        jellyKing.setMoveDirection(true, false);
                        jellyKing.jumpingPoint = true;
                    } else {  // 왼쪽 터치
                        jellyKing.setMoveDirection(false, false);
                        jellyKing.jumpingPoint = true;
                    }
                }
                else {
                    jellyKing.touch = false;
                }
                return true;
            case MotionEvent.ACTION_UP:
                jellyKing.touch = false;
                return true;
        }
        return false;
    }

    public void stageClear() {
        stageNum += 1;
        if(stage.stageOpenNum <= stageNum) {
            stage.stageOpenNum = stageNum;
        }

        if(stageNum > 5) { // 스테이지 5를 성공한 경우, 스테이지 선택화면으로 가도록
            end();
        }
        init();
    }

    public ArrayList<GameObject> objectsAt(Layer layer) {
        return layers.get(layer.ordinal());
    }

    public void add(Layer layer, GameObject gameObject) {
        add(layer.ordinal(), gameObject);
    }

    @Override
    public void start() {
        Log.d(TAG, "start");
        Sound.playMusic(R.raw.bgm);
    }

    @Override
    public void pause() {
        Sound.pauseMusic();
    }

    @Override
    public void resume() {
        Sound.resumeMusic();
    }

    @Override
    public void end() {
        Sound.stopMusic();
    }
}
