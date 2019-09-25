package com.zainco.task.domain.repositories

import com.zainco.task.domain.entities.AdviceEntity
import io.reactivex.Flowable

interface AdviceRepository {
    fun getAdvice(): Flowable<AdviceEntity>

}