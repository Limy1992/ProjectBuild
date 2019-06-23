package com.lmy.base.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lmy.base.ui.ActivityManger

/**
 * supper activity
 * Create by 2019-06-23
 * Author lmy
 */
open class SuperClassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityManger.addActivity(this)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        ActivityManger.removeActivity(this)
        super.onDestroy()
    }
}