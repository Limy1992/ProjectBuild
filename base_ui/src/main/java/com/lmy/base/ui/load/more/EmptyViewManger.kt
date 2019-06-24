package com.lmy.base.ui.load.more

import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.lmy.base.ui.ActivityManger

/**
 * BaseQuickAdapter 空视图
 * CreateDate:2019/6/24
 * Author:lmy
 */
class EmptyViewManger {
    fun getEmptyView():View{
        val textView = TextView(ActivityManger.getContext())
//        textView.setText(text)
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17f)
//        textView.setTextColor(mContext.getResources().getColor(R.color.v3_black))
//        textView.gravity = Gravity.CENTER
        return textView
    }
}