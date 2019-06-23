package com.lmy.base.ui.activity

import kotlinx.android.synthetic.main.activity_title_base_ui.*

/**
 * titleBar
 * Create by 2019-06-23
 * Author lmy
 */
class SuperToolbarActivity : SuperTopBarBaseActivity() {

    fun setTitleText(title: String) {
        toolBar.title = title
    }

    fun setTitleText(resId: Int) {
        toolBar.title = getString(resId)
    }
}