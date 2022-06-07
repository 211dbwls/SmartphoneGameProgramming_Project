package com.example.jellyking.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import com.example.jellyking.BuildConfig;
import com.example.jellyking.R;
import com.example.jellyking.framework.interfaces.BoxCollidable;
import com.example.jellyking.framework.interfaces.GameObject;
import com.example.jellyking.framework.interfaces.Touchable;
import com.example.jellyking.framework.res.Metrics;
import com.example.jellyking.framework.view.GameView;

import java.util.ArrayList;

public class Scene {
    private static final String TAG = Scene.class.getSimpleName();

    protected static Scene singleton;

    public static Scene getInstance() {
       return singleton;
    }
    protected ArrayList<ArrayList<GameObject>> layers;

    public float frameTime;

    public float size(float unit) {
        return Metrics.height / 9.5f * unit;
    }

    public static void clear() {
        singleton = null;
    }

    public void init() {
    }

    public void start(){}
    public void pause(){}
    public void resume(){}
    public void end(){}

    public void initLayers(int count) {  // layer 초기화
        layers = new ArrayList<>();
        for(int i = 0;i < count;i++) {
            layers.add(new ArrayList<>());
        }
    }

    public void draw(Canvas canvas) {
        for (ArrayList<GameObject> gameObjects : layers) {
            for (GameObject gobj : gameObjects) {
                gobj.draw(canvas);
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

    public void add(int layerIndex, GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> gameObjects = layers.get(layerIndex);
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
