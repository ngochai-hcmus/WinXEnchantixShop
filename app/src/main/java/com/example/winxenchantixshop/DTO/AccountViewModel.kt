package com.example.winxenchantixshop.DTO

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountViewModel : ViewModel(){

    private val  repository : AccountRepsitory
    private val _allAccounts  = MutableLiveData<List<Account>>()
    val AllAccounts : LiveData<List<Account>> = _allAccounts

    init{

        repository = AccountRepsitory().getInstance()
        repository.loadAccount(_allAccounts)
    }

}