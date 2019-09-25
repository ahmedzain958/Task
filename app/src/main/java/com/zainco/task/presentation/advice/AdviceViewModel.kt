package com.zainco.task.presentation.advice

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.zainco.task.domain.usecase.GetAdviceUseCase
import com.zainco.task.presentation.common.BaseViewModel

class AdviceViewModel(private val getAdviceUseCase: GetAdviceUseCase) : BaseViewModel() {
    var advice = MutableLiveData<String>()
    companion object {
        private val TAG = "adviceviewModel"
        private const val STATIC_ADVICE = "A penny saved is a penny earned"
    }
    fun fetchAdvice() {

        val disposable = getAdviceUseCase.getAdvice()
            .map {
                return@map it.fortune.joinToString(separator = " ")
            }
            .subscribe({ response: String ->
                advice.value = response
            }, {
                advice.value = STATIC_ADVICE
            }, {
                Log.d(TAG, "On Complete Called")
            })

        addDisposable(disposable)
    }

    fun getAdviceLiveData() = advice
}