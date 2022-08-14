package com.example.winxenchantixshop.DTO

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.winxenchantixshop.DAO.UserInforDAO

class UserInforViewModel {
    private val  repository : UserInforDAO
    private val _allUsers  = MutableLiveData<List<UserInfor>>()
    val AllUsers : LiveData<List<UserInfor>> = _allUsers

    init{

        repository = UserInforDAO().getInstance()
        repository.loadUsers(_allUsers)
    }
}