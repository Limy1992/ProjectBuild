package com.lmy.base.ui.activity

import android.view.Menu
import android.view.MenuItem
import com.lmy.base.ui.R
import kotlinx.android.synthetic.main.activity_title_base_ui.*

/**
 * titleBar
 * Create by 2019-06-23
 * Author lmy
 */
open class SuperToolbarActivity : SuperTopBarBaseActivity() {
    /**左边图片按钮一般是关闭按钮*/
    private var leftIconResId: Int = 0
    private var mLeftOnClickListener: OnLeftClickListener? = null

    /**右边菜单文本按钮*/
    private var mRightStringButton: String? = null
    /**右边菜单的点击事件*/
    private var mRightOnClickListener: OnRightClickListener? = null
    /**右边菜单的图片按钮*/
    private var mRightIconResId: Int = 0


    fun getTitleText():String{
        return ""
    }

    fun setTitleText(title: String) {
        toolBar.title = title
    }

    fun setTitleText(resId: Int) {
        toolBar.title = getString(resId)
    }

    /**
     * 更换左边icon
     * @param iconResId 图片资源
     */
    fun setReplaceTopLeftButton(iconResId: Int) {
        setTopLeftButton(iconResId, null)
    }

    /**
     * 更换左边icon
     * @param iconResId 图片资源
     * @param onClickListener 点击事件
     */
    fun setReplaceTopLeftButton(iconResId: Int, onClickListener: OnLeftClickListener) {
        setTopLeftButton(iconResId, onClickListener)
    }

    /**
     * 左边icon设置
     */
    private fun setTopLeftButton(iconResId: Int, onClickListener: OnLeftClickListener?) {
        this.leftIconResId = iconResId
        this.mLeftOnClickListener = onClickListener
        toolBar.setNavigationIcon(iconResId)
    }

    /**
     * 顶部右边图片按钮
     * @param iconResId 图片资源
     * @param onClickListener 事件点击
     */
    fun setTopRightButton(iconResId: Int, onClickListener: OnRightClickListener) {
        this.mRightIconResId = iconResId
        this.mRightOnClickListener = onClickListener
    }

    /**
     * 顶部右边图片按钮
     * @param stringTitle 文本
     * @param onClickListener 事件点击
     */
    fun setTopRightButton(stringTitle: String, onClickListener: OnRightClickListener) {
        this.mRightStringButton = stringTitle
        this.mRightOnClickListener = onClickListener
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_base_top_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if (mRightIconResId != 0) {
            menu.findItem(R.id.menu_1).setIcon(mRightIconResId)
        }
        if (mRightStringButton?.isNotEmpty() == true) {
            menu.findItem(R.id.menu_1).setTitle(mRightIconResId)
        }
        modifyRightMenu(menu)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> mLeftOnClickListener?.onClick() ?: finish()
            R.id.menu_1 -> mRightOnClickListener?.onClick()
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * 重写这个方法，修改菜单状态
     */
    protected open fun modifyRightMenu(menu: Menu){
    }


    interface OnLeftClickListener {
        fun onClick()
    }

    interface OnRightClickListener {
        fun onClick()
    }
}