package com.example.myapplication.data.network

import com.example.myapplication.data.model.LoginRequest
import com.example.myapplication.data.model.ResponseCli
import retrofit2.http.Body
import  retrofit2.http.POST


interface LoginDbService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): ResponseCli
}