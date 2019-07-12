package com.lmy.base.ui.load.more

import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.lmy.base.ui.ActivityManger
import com.lmy.base.ui.R

/**
 * BaseQuickAdapter 空视图
 * CreateDate:2019/6/24
 * Author:lmy
 */
class EmptyViewManger {
    fun getEmptyView():View{
        val context = ActivityManger.getContext()
        val textView = TextView(context)
        textView.text = "空视图"
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17f)
        textView.setTextColor(context.resources.getColor(R.color.color_333333))
        textView.gravity = Gravity.CENTER
        return textView
    }
}