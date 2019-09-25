package com.zainco.task.base.data.resources.repository

import com.hogroup.uctd.base.data.resources.AppResources
import com.zainco.task.R

class ResourcesRepository(private val appResources: AppResources) {
    fun getNetworkExceptionMessage(): String = appResources.getString(R.string.no_internet_connection)
    fun getSocketTimeoutExceptionMessage(): String = appResources.getString(R.string.timeout_error_message)
    fun getGenericUnknownErrorMessage(): String = appResources.getString(R.string.generic_unknown_error)
    fun getNoContentErrorMessage(): String = appResources.getString(R.string.no_content_error)
}