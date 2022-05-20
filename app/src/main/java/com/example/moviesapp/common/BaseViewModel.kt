package com.example.moviesapp.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.data.utils.NetworkUtils
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import androidx.lifecycle.viewModelScope

abstract class BaseViewModel<T : Any, E> : ViewModel(), KoinComponent {
    private val networkUtils: NetworkUtils by inject()

    protected val state = MutableLiveData<ViewState<T>>()
    val viewState: LiveData<ViewState<T>> get() = state

    protected val effects = MutableLiveData<E>()
    val viewEffects: LiveData<E> get() = effects

    protected fun executeUseCase(action: suspend () -> Unit, noInternetAction: () -> Unit) {
        state.value = Loading()
        if (networkUtils.hasNetworkAccess()) {
            viewModelScope.launch { action() }
        } else {
            noInternetAction()
        }
    }

    protected fun executeUseCase(action: suspend () -> Unit) {
        state.value = Loading()
        viewModelScope.launch { action() }
    }
}