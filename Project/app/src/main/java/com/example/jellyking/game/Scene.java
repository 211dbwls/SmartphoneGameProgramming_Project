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

    protected static ArrayList<Scene> sceneStack = new ArrayList<>();

    public static void start(Scene scene) {
        int lastIndex = sceneStack.size() - 1;
        if (lastIndex >= 0) {
            Scene top = sceneStack.remove(lastIndex);
            Log.d(TAG, "Ending(in start): " + top);
            top.end();
            sceneStack.set(lastIndex, scene);
        } else {
            sceneStack.add(scene);
        }
        Log.d(TAG, "Starting(in start): " + scene);
        scene.start();
    }

    public static void push(Scene scene) {
        int lastIndex = sceneStack.size() - 1;
        if (lastIndex >= 0) {
            Scene top = sceneStack.get(lastIndex);
            Log.d(TAG, "Pausing: " + top);
            top.pause();
        }
        sceneStack.add(scene);
        Log.d(TAG, "Starting(in push): " + scene);
        scene.start();
    }

    public static void popScene() {
        int lastIndex = sceneStack.size() - 1;
        if (lastIndex >= 0) {
            Scene top = sceneStack.remove(lastIndex);
            Log.d(TAG, "Ending(in pop): " + top);
            top.end();
        }
        lastIndex--;
        if (lastIndex >= 0) {
            Scene top = sceneStack.get(lastIndex);
            Log.d(TAG, "Resuming: " + top);
            top.resume();
        } else {
            Log.e(TAG, "should end app in popScene()");
        }
    }

    public void init() {
    }
    public boolean isTransparent() { return false; }
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
        draw(canvas, sceneStack.size() - 1);
    }

    protected void draw(Canvas canvas, int index) {
        Scene scene = sceneStack.get(index);
        if (scene.isTransparent() && index > 0) {
            draw(canvas, index - 1);
        }
        ArrayList<ArrayList<GameObject>> layers = scene.layers;
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
        if (layers == null) return 0;
        int count = 0;
        for(ArrayList<GameObject> gameObjects : layers) {
            count += gameObjects.size();
        }
        return count;
    }
    public boolean onTouchEvent(MotionEvent event) {
        int touchLayer = getTouchLayerIndex();
        if (touchLayer < 0) {
            return false;
        }

        ArrayList<GameObject> gameObjects = layers.get(touchLayer);
        for (GameObject gobj : gameObjects) {
            if (!(gobj instanceof Touchable)) {
                continue;
            }
            boolean processed = ((Touchable) gobj).onTouchEvent(event);
            if (processed) return true;
        }
        return false;
    }

    protected int getTouchLayerIndex() {
        return -1;
    }

    public ArrayList<GameObject> objectsAt(int layerIndex) {
        return layers.get(layerIndex);
    }

    public void finish() {
        GameView.view.getActivity().finish();
    }

    public boolean handleBackKey() {
        return false;
    }
}
