package com.zainco.task.data.api

import com.zainco.task.domain.entities.AdviceEntity
import io.reactivex.Flowable
import retrofit2.http.GET

interface OrganizationRemoteApi {
    @GET("fortune")
    fun getAdvice(): Flowable<AdviceEntity>

}