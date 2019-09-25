package com.zainco.task

import android.app.Application
import com.zainco.task.base.data.remote.remoteModule
import com.zainco.task.base.data.resources.di.resourcesModule
import com.zainco.task.presentation.advice.di.adviceModule
import org.koin.android.ext.koin.with
import org.koin.standalone.StandAloneContext

fun startKoin(application: Application) {
    StandAloneContext.startKoin(
        listOf(
            resourcesModule,
            remoteModule,
            adviceModule
        )
    ) with application
}