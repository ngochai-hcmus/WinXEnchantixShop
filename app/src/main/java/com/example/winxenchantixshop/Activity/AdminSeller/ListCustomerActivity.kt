package com.example.winxenchantixshop.Activity.AdminSeller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Adapter.AccountAdapter
import com.example.winxenchantixshop.Adapter.ProductAdapter
import com.example.winxenchantixshop.DTO.Account
import com.example.winxenchantixshop.DTO.Product
import com.example.winxenchantixshop.DTO.ProductView
import com.example.winxenchantixshop.databinding.ActivityListCustomerBinding
import com.example.winxenchantixshop.R
import com.google.firebase.database.*

class ListCustomerActivity : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var AccountRecyclerview: RecyclerView
    private lateinit var AccountArrayList: ArrayList<Account>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_customer)
        AccountRecyclerview = findViewById(R.id.recycle_list_customer)
        AccountRecyclerview.layoutManager = LinearLayoutManager(this)
        AccountRecyclerview.setHasFixedSize(true)

        AccountArrayList = arrayListOf<Account>()
        getAccountData()


    }

    private fun getAccountData() {
        dbref = FirebaseDatabase.getInstance().getReference("Account")
        val okQuery = dbref.orderByChild("type").equalTo("customer")
        okQuery.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (accountSnapshot in snapshot.children) {
                        val account = accountSnapshot.getValue(Account::class.java)

                        AccountArrayList.add(account!!)

                    }

                    AccountRecyclerview.adapter = AccountAdapter(AccountArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}