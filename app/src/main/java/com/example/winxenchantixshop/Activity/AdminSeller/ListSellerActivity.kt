package com.example.winxenchantixshop.Activity.AdminSeller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Adapter.AccountAdapter
import com.example.winxenchantixshop.R
import com.google.firebase.database.*
import com.example.winxenchantixshop.DTO.Account

class ListSellerActivity : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var AccountRecyclerview: RecyclerView
    private lateinit var AccountArrayList: ArrayList<Account>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_seller)
        AccountRecyclerview = findViewById(R.id.recycle_list_seller)
        AccountRecyclerview.layoutManager = LinearLayoutManager(this)
        AccountRecyclerview.setHasFixedSize(true)

        AccountArrayList = arrayListOf<Account>()
        getAccountData()

    }

    private fun getAccountData() {
        dbref = FirebaseDatabase.getInstance().getReference("Account")
        val okQuery = dbref.orderByChild("type").equalTo("seller")
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





