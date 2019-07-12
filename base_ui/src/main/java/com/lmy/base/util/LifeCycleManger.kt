package com.lmy.base.util

import com.trello.rxlifecycle3.LifecycleTransformer
import java.util.*

/**
 * 生命感知管理
 * CreateDate:2019/7/12
 * Author:lmy
 */
object LifeCycleManger {

    private val lifeCycleStack = Stack<LifecycleTransformer<Any>>()

    fun addDestroyLifeCycle(destroyLifeCycle:LifecycleTransformer<Any>){
        lifeCycleStack.add(destroyLifeCycle)
    }

    fun removeDestroyLifeCycle(destroyLifeCycle:LifecycleTransformer<Any>){
        lifeCycleStack.remove(destroyLifeCycle)
    }

    fun getDestroyLifeCycle():LifecycleTransformer<Any>{
        return lifeCycleStack.lastElement()
    }

}