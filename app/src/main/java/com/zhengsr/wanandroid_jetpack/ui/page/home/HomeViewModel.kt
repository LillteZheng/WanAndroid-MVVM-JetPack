package com.zhengsr.wanandroid_jetpack.ui.page.home

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhengsr.wanandroid_jetpack.bean.BannerBean
import com.zhengsr.wanandroid_jetpack.bean.LoopBannerBean
import com.zhengsr.wanandroid_jetpack.ui.interfaces.ArticleListPagingInterface
import com.zhengsr.wanandroid_jetpack.ui.interfaces.impl.ArticleListImpl

/**
 * @author by zhengshaorui 2022/1/24
 * describe：
 */
class HomeViewModel : ViewModel(),ArticleListPagingInterface by ArticleListImpl() {

    val bannerBeans = MutableLiveData<LoopBannerBean>()
    val isCollected = ObservableBoolean()
    //轮播图状态
    val bannerAnim = ObservableBoolean()
}