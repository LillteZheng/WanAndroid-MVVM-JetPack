package com.zhengsr.wanandroid_jetpack.ui.page.home

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhengsr.wanandroid_jetpack.bean.ArticleData
import com.zhengsr.wanandroid_jetpack.bean.BannerBean
import com.zhengsr.wanandroid_jetpack.bean.LoopBannerBean
import com.zhengsr.wanandroid_jetpack.net.getApi
import com.zhengsr.wanandroid_jetpack.utils.scopeIo
import com.zhengsr.wanandroid_jetpack.utils.withMain
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * @author by zhengshaorui 2022/1/24
 * describe：
 */
class HomeViewModel : ViewModel() {
    private val TAG = "HomeViewModel"
    private val START_PAGE_NUM = 0;
    val bannerList = MutableLiveData<LoopBannerBean>()
    val articleList = MutableLiveData<List<ArticleData>>()
    val isCollected = ObservableBoolean()

    //轮播图状态
    val bannerAnim = ObservableBoolean()


    fun refresh() {
        scopeIo {
            val banner = async {
                val loopBannerBean = LoopBannerBean()
                loopBannerBean.data = getApi().getBanner().data
                loopBannerBean
            }
            val article = async {
                val topArticle = getApi().getTopArticle().data
                val datas = getApi().getArticle(START_PAGE_NUM).data.datas
                topArticle.addAll(datas)
                topArticle
            }
            Log.d(TAG, "zsr refresh: ")
            bannerList.postValue(banner.await())
            articleList.postValue(article.await())

        }
    }

}