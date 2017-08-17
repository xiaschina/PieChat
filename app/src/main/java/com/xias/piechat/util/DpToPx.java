package com.xias.piechat.util;

import android.content.Context;

/**
 * Created by bjhl on 2017/8/15.
 */

public class DpToPx {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
