package com.lmy.base.ui.load.more

/**
 * 加载更多
 * CreateDate:2019/6/24
 * Author:lmy
 */
interface OnLoadMoreDataListener {
    //刷新数据
    fun onRequestData()
    //设置新数据
    fun onNewData()
    //添加数据
    fun onAddData()
    //当前页面数据大小，用于判断是否加载完毕
    fun getDataSize():Int
    //第一次加载就出现错误
    fun onLoadErr(){}
}