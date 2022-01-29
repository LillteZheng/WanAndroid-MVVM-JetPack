package com.zhengsr.wanandroid_jetpack.net

import com.zhengsr.wanandroid_jetpack.bean.ArticleData
import com.zhengsr.wanandroid_jetpack.bean.BannerBean
import com.zhengsr.wanandroid_jetpack.bean.BaseResponse
import com.zhengsr.wanandroid_jetpack.bean.PageDataInfo
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author by zhengshaorui 2022/1/28
 * describe：wanandroid 接口
 */
interface HttpServerApi {
    /**
     * https://www.wanandroid.com/banner/json
     * 获取 Banner 数据
     * @return
     */
    @GET("banner/json")
    suspend fun getBanner(): BaseResponse<List<BannerBean>>

    /**
     * 获取文章
     * https://www.wanandroid.com/article/list/num/json
     * @param num 页码
     * @return
     */
    @GET("article/list/{num}/json")
    suspend fun getArticle(@Path("num") num: Int): BaseResponse<PageDataInfo<List<ArticleData>>>
}