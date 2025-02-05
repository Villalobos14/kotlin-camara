package com.example.myapplication.data.repository

import com.example.myapplication.data.network.ApiClient
import com.example.myapplication.data.network.LoginDbService
import com.example.myapplication.data.model.LoginRequest


class LoginRepository {
    private val api: LoginDbService = ApiClient.retrofit.create(LoginDbService::class.java)
    suspend fun  login(email:String,password:String): Boolean {
       return  try {
           api.login(LoginRequest(email,password)).success
        }catch (e:Exception){
            false

        }

    }
}