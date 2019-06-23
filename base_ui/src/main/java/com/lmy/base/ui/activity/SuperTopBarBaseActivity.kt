package com.lmy.base.ui.activity

import android.os.Bundle
import com.lmy.base.ui.R


/**
 * title Activity
 * CreateDate:2019/6/15
 * Author:lmy
 */
open class SuperTopBarBaseActivity : SuperClassActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_base_ui)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}