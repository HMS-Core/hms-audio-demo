/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */
package com.huawei.hms.audiokitdemo.utils;

import android.content.Context;

import com.huawei.hms.audiokitdemo.HwAudioApplication;

/**
 * ResUtils
 *
 * @since 2021-3-29
 */
public class ResUtils {
    private static final float HALF = 0.5f;

    /**
     * dp转为px
     *
     * @param dp dp
     * @return dp转为px值
     */
    public static int dp2Px(Context context, float dp) {
        final float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + HALF);
    }

    public static String getString(int id) {
        return HwAudioApplication.getContext().getResources().getString(id);
    }

}
