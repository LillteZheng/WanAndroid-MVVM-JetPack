package com.zhengsr.wanandroid_jetpack.ui.page.home

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhengsr.wanandroid_jetpack.bean.BannerBean

/**
 * @author by zhengshaorui 2022/1/24
 * describe：
 */
class HomeViewModel : ViewModel() {

    val bannerBeans = MutableLiveData<List<BannerBean>>()
    val isCollected = ObservableBoolean()
}