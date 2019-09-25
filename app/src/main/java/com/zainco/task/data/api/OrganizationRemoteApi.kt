package com.zainco.task.data.api

interface OrganizationRemoteApi {
    @GET("top-headlines?country=us")
    fun getNews(): Flowable<NewsSourcesData>

}