package com.lmy.base.util

import android.annotation.SuppressLint
import android.content.Context

/**
 * app
 * CreateDate:2019/7/12
 * Author:lmy
 */
//去除黄色警告
@SuppressLint("StaticFieldLeak")
object APPUtils {
    private var context: Context? = null     //注意使用,不会照成内存泄漏,

    private fun Utils(): Throwable {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    fun init(context: Context) {
        APPUtils.context = context.applicationContext
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    fun getContext(): Context {
        if (context != null) return context!!
        throw NullPointerException("u should init first")
    }

    /**
     * dp转换
     */
    fun dip2px(dpValue: Float): Int {
        val scale = APPUtils.getContext().resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(pxValue: Float): Int {
        val scale = APPUtils.getContext().resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

}