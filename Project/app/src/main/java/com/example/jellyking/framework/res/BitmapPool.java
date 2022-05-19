package com.example.jellyking.framework.res;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.jellyking.framework.view.GameView;

import java.util.HashMap;

public class BitmapPool {
    private static HashMap<Integer, Bitmap> bitmaps = new HashMap<>();

    public static Bitmap get(int mipmapResId) {
        Bitmap bitmap = bitmaps.get(mipmapResId);
        if (bitmap == null) {
            Resources res = GameView.view.getResources();
            bitmap = BitmapFactory.decodeResource(res, mipmapResId);
            bitmaps.put(mipmapResId, bitmap);
        }

        return bitmap;
    }
}
