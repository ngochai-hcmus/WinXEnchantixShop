package com.example.winxenchantixshop.DTO

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.winxenchantixshop.DAO.ProductDAO

class ProductView : ViewModel() {
    private val repository : ProductDAO
    private val _allProducts = MutableLiveData<List<Product>>()
    val allUsers : LiveData<List<Product>> = _allProducts


    init {

        repository = ProductDAO().getInstance()
        repository.loadProducts(_allProducts)

    }

}