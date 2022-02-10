package com.zhengsr.wanandroid_jetpack.ui.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhengsr.wanandroid_jetpack.bean.ArticleData
import com.zhengsr.wanandroid_jetpack.bean.LoopBannerBean
import com.zhengsr.wanandroid_jetpack.net.getApi
import com.zhengsr.wanandroid_jetpack.utils.START_PAGE_NUM
import com.zhengsr.wanandroid_jetpack.utils.apiCall
import com.zhengsr.wanandroid_jetpack.utils.judge
import com.zhengsr.wanandroid_jetpack.utils.scopeIo
import kotlinx.coroutines.async

/**
 * @author by zhengshaorui 2022/1/24
 * describe：
 */
class HomeViewModel : BaseViewModel() {
    private val TAG = "HomeViewModel"
    val pageNum = MutableLiveData<Int>(START_PAGE_NUM)
    val bannerList = MutableLiveData<LoopBannerBean?>()
    val articleList = MutableLiveData<List<ArticleData>?>()
    val isCollected = ObservableBoolean()

    //轮播图状态
    val bannerAnim = ObservableBoolean()


    fun onLoadMore() {
        val value = pageNum.value?.let {
            it + 1
        } ?: 1
        pageNum.value = value
        scopeIo {
            try {
                pageNum.value?.let {
                    getApi().getArticle(it).judge(
                        onSuccess = {
                            articleList.postValue(this.data.datas)
                        }, onFailed = defaultFailedBlock()
                    )

                }
            } catch (e: Exception) {
                defaultFailedBlock()
            }
        }
    }


    fun onRefresh() {
        scopeIo {
            var isSuccess = true
            val banner = async {
                val loopBannerBean = LoopBannerBean()
                apiCall { getApi().getBanner() }.judge(
                    onSuccess = {
                        loopBannerBean.data = this.data.orEmpty()
                    }, onFailed = { isSuccess = false }
                )
                loopBannerBean
            }
            val article = async {
                //判断一个即可
                val articles = mutableListOf<ArticleData>()
                apiCall { getApi().getTopArticle() }.judge(
                    onSuccess = {
                        articles.addAll(this.data)
                    },
                    onFailed = { isSuccess = false }
                )
                pageNum.postValue(START_PAGE_NUM)
                apiCall { getApi().getArticle(START_PAGE_NUM) }.judge(
                    onSuccess = {
                        val datas = this.data.datas
                        articles.addAll(datas)
                    }, onFailed = { isSuccess = false }
                )

                articles
            }
            val bannerAwait = banner.await()
            val articleAwait = article.await()
            if (isSuccess) {
                bannerList.postValue(bannerAwait)
                articleList.postValue(articleAwait)
            } else {
                defaultFail()
            }


        }

    }

}