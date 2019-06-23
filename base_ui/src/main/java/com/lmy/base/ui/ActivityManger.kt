package com.lmy.base.ui

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentActivity
import java.util.*

/**
 * activity管理
 * Create by 2019-06-23
 * Author lmy
 */
object ActivityManger {
    private val stack = Stack<Activity>()

    fun addActivity(activity: Activity){
        stack.add(activity)
    }

    fun removeActivity(activity: Activity){
        stack.remove(activity)
    }

    fun getFragmentActivity():FragmentActivity{
        return stack.lastElement() as FragmentActivity
    }

    fun getContext():Context{
        return stack.lastElement()
    }
}