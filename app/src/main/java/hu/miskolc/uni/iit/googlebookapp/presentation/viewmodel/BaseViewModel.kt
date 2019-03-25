package hu.miskolc.uni.iit.googlebookapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import hu.miskolc.uni.iit.googlebookapp.domain.usecase.UseCase

abstract class BaseViewModel(
    vararg useCases: UseCase
) : ViewModel() {

    private val useCaseList: MutableList<UseCase> = mutableListOf()

    init {
        useCaseList.addAll(useCases)
    }

    override fun onCleared() {
        super.onCleared()
        useCaseList.forEach { it.dispose() }
    }

}