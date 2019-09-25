package com.zainco.task.presentation.advice

import androidx.lifecycle.MutableLiveData
import com.zainco.task.domain.usecase.GetAdviceUseCase
import com.zainco.task.presentation.common.BaseViewModel

class AdviceViewModel(private val getAdviceUseCase: GetAdviceUseCase) : BaseViewModel() {
    private var advice = MutableLiveData<String>()
     val isLoad = MutableLiveData<Boolean>()

    init {
        isLoad.value = false
    }

    companion object {
        private const val STATIC_ADVICE = "A penny saved is a penny earned"
    }

    fun fetchAdvice() {
        val disposable = getAdviceUseCase.getAdvice()
            .map {
                //joins list into a string and replaces any comma ,
                return@map it.fortune.joinToString(separator = " ")
            }
            .subscribe({ response: String ->
                isLoad.value = true
                advice.value = response
            }, {
                isLoad.value = true
                advice.value = STATIC_ADVICE
            })

        addDisposable(disposable)
    }

    fun getAdviceLiveData() = advice
}