package com.zainco.task.base.data.remote

import com.google.gson.GsonBuilder
import com.zainco.task.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@JvmField
val remoteModule = module {
    single { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }
    //todo: ErrorMappingInterceptor  will be used when u have a format error response
//    single { ErrorMappingInterceptor(get(), get()) }
    single<OkHttpClient> {
        val builder: OkHttpClient.Builder =
            OkHttpClient.Builder().hostnameVerifier { _, _ -> true }
        builder
            .connectTimeout(RemoteConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(RemoteConstants.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(RemoteConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(get<HttpLoggingInterceptor>())
        }
        builder.build()
    }
    single { GsonBuilder().serializeNulls().create() }

    single<Retrofit> {
        Retrofit.Builder()
                //todo: uncomment this line to let the code use the base url
//            .baseUrl(BuildConfig.BASE_LIVE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }
}

