package com.example.jellyking.game;

import com.example.jellyking.framework.res.Metrics;
import com.example.jellyking.framework.object.Sprite;

public class Background extends Sprite {
    public Background(int bitmapResId) {
        super(Metrics.width / 2, Metrics.height / 2, (float) Metrics.width, (float) Metrics.height, bitmapResId);

        float height = bitmap.getHeight() * Metrics.width / bitmap.getWidth();  // 이미지의 폭에 따른 높이를 구함
        // ImageWidth :  ImageHeight = ScreenWidth : ScreenHeight
        // ImageWidth * ScreenHeight = ImageHeight * ScreenWidth
        // ScreenHeight = ImageHeight * ScreenWidth / ImageWidth

        float width = bitmap.getHeight() * Metrics.width / bitmap.getWidth();  // 이미지의 폭에 따른 높이를 구함
        // ImageWidth :  ImageHeight = ScreenWidth : ScreenHeight
        // ImageHeight * ScreenWidth = ImageWidth * ScreenHeight
        // ScreenWidth = ImageWidth * ScreenHeight / ImageHeight

        setDstRect(width, Metrics.height);
    }
}
