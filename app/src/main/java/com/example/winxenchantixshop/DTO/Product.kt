package com.example.winxenchantixshop.DTO

import java.io.Serializable

data class Product(
    val imageUrl: String? = null,
    val productName: String? = null,
    val category: String? = null,
    val price: String? = null,
    val amount: String? = null,
    val desciption: String? = null) : Serializable{

}
