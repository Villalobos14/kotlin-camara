package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel(){

    private val repository =LoginRepository()

    private val _loginState = MutableStateFlow(false)
    val loginState: StateFlow<Boolean> = _loginState
    fun login(email:String,password:String,onResult:(Boolean)->Unit){
        viewModelScope.launch {
            val result=repository.login(email,password)
            _loginState.value=result
            onResult(result)
        }
    }
}