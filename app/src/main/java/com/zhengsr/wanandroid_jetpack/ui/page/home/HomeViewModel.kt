package com.zhengsr.wanandroid_jetpack.ui.page.home

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhengsr.wanandroid_jetpack.bean.ArticleData
import com.zhengsr.wanandroid_jetpack.bean.LoopBannerBean
import com.zhengsr.wanandroid_jetpack.net.getApi
import com.zhengsr.wanandroid_jetpack.utils.START_PAGE_NUM
import com.zhengsr.wanandroid_jetpack.utils.defaultFailedBlock
import com.zhengsr.wanandroid_jetpack.utils.judge
import com.zhengsr.wanandroid_jetpack.utils.scopeIo
import kotlinx.coroutines.async

/**
 * @author by zhengshaorui 2022/1/24
 * describe：
 */
class HomeViewModel : ViewModel() {
    private val TAG = "HomeViewModel"
    val pageNum = MutableLiveData<Int>(START_PAGE_NUM)
    val bannerList = MutableLiveData<LoopBannerBean?>()
    val articleList = MutableLiveData<List<ArticleData>?>()
    val loadMoreArticleList = MutableLiveData<List<ArticleData>?>()
    val isCollected = ObservableBoolean()

    //轮播图状态
    val bannerAnim = ObservableBoolean()


    val onLoadMore: () -> Unit = {
        val value = pageNum.value?.let {
            it + 1
        } ?: 1
        pageNum.value = value
        scopeIo {
            try {
                pageNum.value?.let {
                    getApi().getArticle(it).judge(
                        onSuccess = {
                            loadMoreArticleList.postValue(this.data.datas)
                        }, onFailed = defaultFailedBlock
                    )

                }
            } catch (e: Exception) {
            }
        }
    }
    val onRefresh: () -> Unit = {
        scopeIo {
            try {
                var isSuccess = true
                val banner = async {
                    val loopBannerBean = LoopBannerBean()
                    getApi().getBanner().judge(
                        onSuccess = {
                            loopBannerBean.data = this.data.orEmpty()
                        }, onFailed = { isSuccess = false }
                    )
                    loopBannerBean
                }
                val article = async {
                    //判断一个即可
                    val topArticle = getApi().getTopArticle().data
                    pageNum.postValue(START_PAGE_NUM)
                    getApi().getArticle(START_PAGE_NUM).judge(
                        onSuccess = {
                            val datas = this.data.datas
                            topArticle.addAll(datas)
                        }, onFailed = { isSuccess = false }
                    )

                    topArticle
                }
                if (isSuccess) {
                    bannerList.postValue(banner.await())
                    articleList.postValue(article.await())
                } else {
                    //todo 错误统一处理
                }
            } catch (e: Exception) {
            }

        }
    }

}