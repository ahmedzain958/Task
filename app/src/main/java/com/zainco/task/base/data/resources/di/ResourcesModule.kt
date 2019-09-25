package com.zainco.task.base.data.resources.di

import com.hogroup.uctd.base.data.resources.AppResources
import com.zainco.task.base.data.resources.repository.ResourcesRepository
import org.koin.dsl.module.module

val resourcesModule = module {
    single { AppResources(get()) }
    single { ResourcesRepository(get()) }
}