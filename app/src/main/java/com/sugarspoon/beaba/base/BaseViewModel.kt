package com.sugarspoon.beaba.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<Intent, State> : ViewModel() {

    protected val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    abstract fun handle(intent: Intent)

}
