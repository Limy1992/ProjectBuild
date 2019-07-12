package com.lmy.base.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lmy.base.util.ActivityManger
import com.lmy.base.util.LifeCycleManger
import com.trello.rxlifecycle3.LifecycleTransformer
import com.trello.rxlifecycle3.RxLifecycle
import com.trello.rxlifecycle3.android.ActivityEvent
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * supper activity
 * Create by 2019-06-23
 * Author lmy
 */
open class SuperClassActivity : RxAppCompatActivity() {
    private lateinit var destroyLifecycle: LifecycleTransformer<Any>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityManger.addActivity(this)
        destroyLifecycle = bindUntilEvent(ActivityEvent.DESTROY)
        LifeCycleManger.addDestroyLifeCycle(destroyLifecycle)
    }

    override fun onResume() {

        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        ActivityManger.removeActivity(this)
        LifeCycleManger.removeDestroyLifeCycle(destroyLifecycle)
        super.onDestroy()
    }
}