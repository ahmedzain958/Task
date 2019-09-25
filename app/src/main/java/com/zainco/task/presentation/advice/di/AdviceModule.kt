package com.zainco.task.presentation.advice.di

import com.zainco.task.data.api.OrganizationRemoteApi
import com.zainco.task.data.repository.AdviceRemoteDataSourceImpl
import com.zainco.task.data.repository.AdviceRemoteDatasource
import com.zainco.task.data.repository.AdviceRepositoryImpl
import com.zainco.task.domain.repositories.AdviceRepository
import com.zainco.task.domain.usecase.GetAdviceUseCase
import com.zainco.task.presentation.advice.AdviceViewModel
import com.zainco.task.presentation.common.AsyncFlowableTransformer
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit


val adviceModule = module {
    factory { get<Retrofit>().create(OrganizationRemoteApi::class.java) }
    factory<AdviceRemoteDatasource> { AdviceRemoteDataSourceImpl(get()) }
    factory<AdviceRepository> { AdviceRepositoryImpl(get()) }
    factory { GetAdviceUseCase(transformer = AsyncFlowableTransformer(), repositories = get()) }
    viewModel {
        AdviceViewModel(getAdviceUseCase = get())
    }
}
