package com.example.myapplication.data.repository

import com.example.myapplication.data.network.ApiClient
import com.example.myapplication.data.network.ProductDbService
import com.example.myapplication.data.model.Product
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File



class ProductRepository {
   private val api:ProductDbService=ApiClient.retrofit.create(ProductDbService::class.java)

    suspend  fun getProducts(): List<Product>{

        return api.getProducts()

    }

    suspend  fun getProduct(id:Int): Product {
        return api.getProduct(id)


    }



    suspend fun createProduct(name:String,description:String,price:Int,imageFile:File): Product {
      val nameBody=name.toRequestBody("text/plain".toMediaTypeOrNull())
        val descriptionBody=description.toRequestBody("text/plain".toMediaTypeOrNull())
       val priceBody=price.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val requestBody=imageFile.asRequestBody("image/*".toMediaTypeOrNull())
        val multiPartImage=MultipartBody.Part.createFormData("image",imageFile.name,requestBody)
        return  api.createProduct(nameBody,descriptionBody,priceBody,multiPartImage)

    }


    suspend fun updateProduct(id:Int,product: Product): Product {
        return api.updateProduct(id,product)

    }


    suspend fun deleteProduct(id:Int): Boolean{
        return  api.deleteProduct(id)

    }



}