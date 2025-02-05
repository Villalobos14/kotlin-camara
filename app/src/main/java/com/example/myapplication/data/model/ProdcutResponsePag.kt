package com.example.myapplication.data.model

data class ProdcutResponsePag(
    val currentPage: Int,
    val products: List<Product>,
    val totalpages: Int,
    val totalproducts: Int
)