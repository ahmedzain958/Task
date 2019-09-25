package com.zainco.task.domain.usecase

import com.zainco.task.domain.common.BaseFlowableUseCase
import com.zainco.task.domain.common.FlowableRxTransformer
import com.zainco.task.domain.entities.AdviceEntity
import com.zainco.task.domain.repositories.AdviceRepository
import io.reactivex.Flowable

class GetAdviceUseCase(
    transformer: FlowableRxTransformer<AdviceEntity>,
    private val repositories: AdviceRepository
) : BaseFlowableUseCase<AdviceEntity>(transformer) {

    override fun createFlowable(data: Map<String, Any>?): Flowable<AdviceEntity> {
        return repositories.getAdvice()
    }

    fun getAdvice(): Flowable<AdviceEntity> {
        val data = HashMap<String, String>()
        return single(data)
    }
}