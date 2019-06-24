package com.lmy.base.ui.activity

import android.os.Bundle

/**
 * activity
 * Create by 2019-06-23
 * Author lmy
 */
abstract class BaseActivity : SuperBaseActivity() {
    override fun setChildContextView(savedInstanceState: Bundle?) {
        setContentView(getContextView())
    }

    override fun onStart() {
        super.onStart()
        initView()
        initData()
        initOther()
    }

    abstract fun getContextView(): Int
    abstract fun initView()
    abstract fun initData()
    protected open fun initOther() {}
}