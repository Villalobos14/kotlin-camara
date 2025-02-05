package com.example.myapplication.data.network
import com.example.myapplication.data.model.Product
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.DELETE
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path


interface ProductDbService {
    @GET("api/products")
    suspend  fun getProducts():List<Product>

    @GET("api/products/{id}")
    suspend  fun getProduct(@Path("id") id:Int): Product

    @Multipart

    @POST("api/products")
    suspend fun createProduct(
        @Part("name") name: RequestBody,       // ✅ Cambiado a RequestBody
        @Part("description") description: RequestBody,
        @Part("price") price: RequestBody,
        @Part image: MultipartBody.Part       // 📷 Imagen en multipart
    ): Product

    @PUT("api/products/{id}")
    suspend fun updateProduct(@Path("id") id:Int,@Body product: Product): Product

    @DELETE("api/products/{id}")
    suspend fun deleteProduct(@Path("id") id:Int):Boolean




}