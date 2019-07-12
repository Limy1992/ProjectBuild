package com.lmy.base.util

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentActivity
import java.util.*
import kotlin.collections.ArrayList

/**
 * activity管理
 * Create by 2019-06-23
 * Author lmy
 */
object ActivityManger {
    //打开的activity管理
    private val stack = Stack<Activity>()
    //被标记批量关闭activity
    private var finishActivity: Stack<Activity>? = null


    fun addActivity(activity: Activity) {
        stack.add(activity)
    }

    fun removeActivity(activity: Activity) {
        stack.remove(activity)
        finishActivity?.remove(activity)
    }

    fun getFragmentActivity(): FragmentActivity {
        return stack.lastElement() as FragmentActivity
    }

    fun getContext(): Context {
        return stack.lastElement()
    }

    /**
     * 当前栈顶是否已经添加当前activity
     */
    fun istTopFlagBatchActivity(activity: Activity): Boolean {
        return finishActivity?.size ?: 0 > 0 && finishActivity?.lastElement() == activity
    }

    /**
     * 标记批量关闭activity
     */
    fun flagBatchActivity(activity: Activity) {
        if (finishActivity == null) {
            finishActivity = Stack()
        }

        finishActivity?.add(activity)
    }

    /**
     * 批量关闭已经标记的activity
     */
    fun finishBatchActivity() {
        val finishAct = ArrayList<Activity>()
        finishAct.addAll(finishActivity ?: ArrayList())
        finishActivity?.clear()
        finishActivity = null
        for (activity in finishAct) {
            if (!activity.isFinishing && !activity.isDestroyed) {
                activity.finish()
            }
        }
    }
}