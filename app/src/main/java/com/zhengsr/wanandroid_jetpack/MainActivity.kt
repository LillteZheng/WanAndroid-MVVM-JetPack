package com.zhengsr.wanandroid_jetpack

import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout.SimpleDrawerListener
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zhengsr.wanandroid_jetpack.ui.base.BaseActivity
import com.zhengsr.wanandroid_jetpack.ui.data.MainViewModel

class MainActivity : BaseActivity<MainViewModel>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        event.drawerOpenOrClose.observe(this,{
            state.openDrawer.set(it)
        })
    }
    override fun initViewModel() {
       state = getViewModel(MainViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_main,BR.vm,state)
            .addBindingParam(BR.drawerListener,ListenerHandler())
    }

    inner class ListenerHandler : SimpleDrawerListener() {
        override fun onDrawerOpened(drawerView: View) {
            super.onDrawerOpened(drawerView)
            state.openDrawer.set(true)
        }

        override fun onDrawerClosed(drawerView: View) {
            super.onDrawerClosed(drawerView)
            state.openDrawer.set(false)
        }
    }
}