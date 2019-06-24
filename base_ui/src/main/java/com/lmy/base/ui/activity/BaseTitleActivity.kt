package com.lmy.base.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.activity_title_base_ui.*

/**
 * titleActivity
 * Create by 2019-06-23
 * Author lmy
 */
abstract class BaseTitleActivity : SuperToolbarActivity() {

    override fun setChildContentView(savedInstanceState: Bundle?) {
        LayoutInflater.from(this).inflate(getContentView(), viewContent)
    }

    abstract fun getContentView():Int

    override fun onStart() {
        super.onStart()
        initView()
        initData()
        initOther()
    }

    abstract fun initView()
    abstract fun initData()
    protected fun initOther(){}
}