package com.zainco.task.data.repository

import com.zainco.task.data.api.OrganizationRemoteApi
import com.zainco.task.domain.entities.AdviceEntity
import io.reactivex.Flowable

class AdviceRemoteDataSourceImpl(private val api: OrganizationRemoteApi) : AdviceRemoteDatasource {
    override fun getAdvice(): Flowable<AdviceEntity> {
        return api.getAdvice()
    }
}