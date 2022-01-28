package com.zhengsr.wanandroid_jetpack.ui.page.home

import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zhengsr.wanandroid_jetpack.BR
import com.zhengsr.wanandroid_jetpack.R
import com.zhengsr.wanandroid_jetpack.ui.base.BaseFragment

class HomeFragment : BaseFragment<HomeViewModel>() {
    private val TAG = "HomeFragment"
    override fun initViewModel() {
        state = getViewModel(HomeViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_home,BR.vm,state)
            .addBindingParam(BR.click,ClickProxy())
    }



   inner class ClickProxy {
        fun openMenu() {
            //打开侧边栏
            event.drawerOpenOrClose.value = true
        }


    }
}