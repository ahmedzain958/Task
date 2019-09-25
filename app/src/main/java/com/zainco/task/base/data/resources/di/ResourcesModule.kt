package com.zainco.task.base.data.resources.di

import com.zainco.task.base.data.resources.AppResources
import org.koin.dsl.module.module

val resourcesModule = module {
    single { AppResources(get()) }
}