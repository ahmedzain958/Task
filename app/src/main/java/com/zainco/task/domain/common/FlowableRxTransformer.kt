package com.zainco.task.domain.common

import io.reactivex.FlowableTransformer

abstract class FlowableRxTransformer<T>: FlowableTransformer<T,T>