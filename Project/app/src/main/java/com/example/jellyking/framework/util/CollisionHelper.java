package com.example.jellyking.framework.util;

import android.graphics.RectF;

public class CollisionHelper {
    public static boolean collides(RectF r1, RectF r2) {
        if(r1.left > r2.right) {
            return false;
        }
        if(r1.top > r2.bottom) {
            return false;
        }
        if(r1.right < r2.left) {
            return false;
        }
        if(r1.bottom < r2.top) {
            return false;
        }

        return true;
    }
}
