package com.zainco.task.data.repository

import com.zainco.task.domain.entities.AdviceEntity
import io.reactivex.Flowable

interface AdviceRemoteDatasource {
    fun getAdvice(): Flowable<AdviceEntity>
}