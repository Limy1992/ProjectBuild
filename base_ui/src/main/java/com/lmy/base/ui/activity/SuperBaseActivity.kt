package com.lmy.base.ui.activity

import android.os.Bundle

/**
 * activity
 * Create by 2019-06-23
 * Author lmy
 */
open class SuperBaseActivity : SuperClassActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setChildContextView(savedInstanceState)
    }

    protected open fun setChildContextView(savedInstanceState: Bundle?) {

    }

}