package com.zainco.task.data.repository

import com.zainco.task.domain.entities.AdviceEntity
import com.zainco.task.domain.repositories.AdviceRepository
import io.reactivex.Flowable

class AdviceRepositoryImpl(private val remote: AdviceRemoteDatasource) : AdviceRepository {
    override fun getAdvice(): Flowable<AdviceEntity> {
        return remote.getAdvice()
    }
}