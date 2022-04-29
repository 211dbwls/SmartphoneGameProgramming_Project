package com.example.jellyking.game;

import com.example.jellyking.framework.Metrics;
import com.example.jellyking.game.block.Block;


public class StageInfo {
    /*
    10 : 아무것도 없음.
    21 : Block | 22 : BrokenBlock | 23 : ElectricBlock | 24 : JumpBlock | 25 : StraightLeftBlock | 26 : StraightRightBlock | 27 : MoveLRBlock | 28 : MoveUDBlock
    31 : FixEnemy | 32 : DropEnemy | 33 : MoveEnemy
    41 :JumpOneItem | 42 : JumpInfiniteItem
    51 : Star
     */

    float stage1StartPointX = Metrics.width / 26 * (3 + 19);  // 2288.0
    float stage1StartPointY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 1);  // 360.0
    int[][] stage1Info = {
            {21, 10, 10, 10, 10, 10, 10, 10, 10, 10, 21, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 21},
            {21, 10, 10, 10, 10, 10, 10, 10, 10, 10, 21, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 21},
            {21, 51, 51, 10, 10, 10, 10, 10, 10, 10, 21, 10, 10, 10, 10, 22, 31, 21, 21, 21, 21, 21},
            {21, 10, 10, 10, 10, 10, 10, 10, 10, 10, 21, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 21},
            {21, 10, 10, 10, 10, 10, 10, 10, 10, 10, 21, 51, 10, 10, 10, 10, 10, 10, 10, 10, 10, 21},
            {21, 24, 24, 10, 10, 10, 10, 10, 10, 10, 21, 21, 21, 21, 21, 21, 21, 21, 21, 10, 10, 21},
            {21, 21, 21, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 21},
            {21, 21, 21, 22, 24, 21, 21, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 21},
            {21, 21, 21, 10, 10, 21, 21, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 51, 21},
            {21, 21, 21, 31, 31, 21, 21, 22, 24, 21, 22, 22, 22, 22, 21, 31, 22, 31, 21, 21, 21, 21}
    };

    float stage2StartPointX = Metrics.width / 26 * (3 + 19);  // 2288.0
    float stage2StartPointY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 1 / 2);  // 360.0
    int[][] stage2Info = {
            {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 21},
            {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 21},
            {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 21, 21, 21, 21},
            {10, 22, 31, 22, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 21},
            {10, 10, 10, 10, 31, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 21},
            {10, 10, 10, 10, 10, 31, 24, 10, 24, 10, 10, 10, 24, 10, 10, 10, 24, 10, 10, 10, 10, 21},
            {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 21},
            {10, 10, 10, 10, 10, 10, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 21, 21, 21, 21},
            {26, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 51, 21},
            {31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 21, 21, 21, 21, 21}
    };

    float stage3StartPointX = Metrics.width / 26 * (3 + 1);  // 416.0
    float stage3StartPointY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 1 / 2);  // 360.0
    int[][] stage3Info = {
            {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
            {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 41, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
            {24, 24, 24, 24, 24, 24, 31, 31, 31, 21, 21, 31, 31, 10, 10, 10, 10, 10, 10, 10, 10, 10},
            {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
            {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 51},
            {10, 10, 10, 10, 22, 10, 10, 27, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 21, 21, 21},
            {10, 10, 10, 22, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
            {10, 10, 22, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 31, 31, 31, 31, 31, 31, 31, 31},
            {10, 26, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 51, 51, 51, 51, 51, 51, 51, 31},
            {31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31}
    };

    float stage4StartPointX = Metrics.width / 26 * (3 + 1);  // 312.0
    float stage4StartPointY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 1 / 2);  // 360.0
    int[][] stage4Info = {
            {10, 10, 10, 10, 10, 10, 10, 10, 10, 32, 10, 10, 10, 32, 10, 10, 10, 10, 10, 10, 10, 10},
            {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
            {21, 10, 26, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
            {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
            {10, 10, 10, 10, 10, 10, 32, 10, 10, 10, 10, 10, 10, 10, 10, 10, 32, 10, 10, 10, 10, 10},
            {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
            {10, 10, 10, 10, 10, 10, 10, 10, 42, 10, 10, 10, 10, 10, 42, 10, 10, 10, 10, 10, 10, 10},
            {10, 26, 10, 10, 10, 10, 10, 51, 21, 10, 10, 10, 10, 10, 21, 51, 10, 10, 10, 10, 10, 25},
            {10, 21, 10, 10, 10, 10, 10, 21, 10, 10, 10, 10, 10, 10, 10, 21, 10, 10, 10, 10, 10, 21},
            {10, 10, 31, 31, 31, 31, 31, 10, 10, 10, 10, 10, 10, 10, 10, 10, 31, 31, 31, 31, 31, 10}
    };

    float stage5StartPointX = Metrics.width / 26 * (3 + 13);  // 1664.0
    float stage5StartPointY = Metrics.height / 13 * 3 + (Metrics.height / 13 * 7 / 2);  // 669.0
    int[][] stage5Info = {
            {10, 10, 23, 10, 23, 10, 23, 10, 23, 10, 10, 10, 10, 22, 22, 22, 22, 22, 10, 10, 10, 10},
            {10, 10, 23, 10, 23, 10, 23, 10, 23, 10, 10, 10, 10, 25, 23, 23, 23, 23, 10, 10, 10, 10},
            {10, 10, 23, 51, 23, 51, 23, 51, 23, 51, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
            {10, 10, 23, 24, 23, 24, 23, 24, 23, 24, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
            {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 22, 10, 10, 10, 10, 10, 10, 10},
            {10, 10, 33, 10, 10, 10, 10, 10, 33, 10, 10, 10, 10, 22, 10, 10, 10, 10, 10, 10, 10, 10},
            {10, 10, 10, 10, 23, 23, 23, 10, 10, 10, 10, 10, 22, 10, 10, 10, 10, 10, 10, 10, 10, 10},
            {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 22, 10, 10, 10, 10, 10, 10, 10, 10, 10, 28},
            {10, 10, 42, 10, 10, 10, 10, 10, 10, 10, 22, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
            {21, 21, 21, 10, 10, 31, 10, 10, 21, 21, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 24, 10}
    };
}
