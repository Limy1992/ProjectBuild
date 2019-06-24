package com.lmy.base.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * CreateDate:2019/6/24
 * Author:lmy
 */
open class SuperClassFragment : Fragment() {

   lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = context!!
    }

    override fun onStart() {
        super.onStart()
    }


}