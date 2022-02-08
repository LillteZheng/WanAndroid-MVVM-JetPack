package com.zhengsr.wanandroid_jetpack.ui.interfaces

import androidx.lifecycle.LiveData
import com.zhengsr.wanandroid_jetpack.bean.ArticleData

/**
 * @author by zhengshaorui 2022/2/7
 * describe：文章列表相关的接口
 */
interface ArticleListPagingInterface {
    /** 文章列表数据 */
    val articleListData: LiveData<ArrayList<ArticleData>>
}