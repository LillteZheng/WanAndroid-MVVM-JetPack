package com.zhengsr.wanandroid_jetpack.ui.interfaces.impl

import androidx.lifecycle.LiveData
import com.zhengsr.wanandroid_jetpack.bean.ArticleData
import com.zhengsr.wanandroid_jetpack.ui.interfaces.ArticleListPagingInterface

/**
 * @author by zhengshaorui 2022/2/7
 * describe：
 */
class ArticleListImpl : ArticleListPagingInterface {
    override val articleListData: LiveData<ArrayList<ArticleData>>
        get() = TODO("Not yet implemented")
}