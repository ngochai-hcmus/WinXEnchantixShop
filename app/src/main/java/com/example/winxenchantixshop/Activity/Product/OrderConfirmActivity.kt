package com.example.winxenchantixshop.Activity.Product

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Adapter.ItemConfirmOrderAdapter
import com.example.winxenchantixshop.DTO.ClientInfo
import com.example.winxenchantixshop.DTO.ItemNewOrder
import com.example.winxenchantixshop.DTO.User
import com.example.winxenchantixshop.databinding.ActivityListOrderConfirmedBinding
import com.google.firebase.database.*


class OrderConfirmActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListOrderConfirmedBinding
    private lateinit var itemAdapter : ItemConfirmOrderAdapter
    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var db_ref : DatabaseReference
    private lateinit var listItem: ArrayList<ItemNewOrder>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListOrderConfirmedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentUser = User.Email("")!!.dropLast(10)
        itemRecyclerView = binding.recycleViewListProduct
        itemRecyclerView.layoutManager = LinearLayoutManager(this)
        itemRecyclerView.setHasFixedSize(true)
        listItem = arrayListOf<ItemNewOrder>()
        itemAdapter = ItemConfirmOrderAdapter(listItem)
        getList(currentUser)

    }

    private fun getList(name: String) {
        db_ref = FirebaseDatabase.getInstance().getReference("HistoryOrder")
        db_ref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        val trim = i.key!!.toString().drop(14)
                        if (trim == name) {
                            val item = i.child("ClientInfo").getValue(ClientInfo::class.java)

                            val name = item!!.name
                            val cost = item!!.Cost
                            val date = item!!.Time
                            val bill = i.key.toString()

                            val itemNew = ItemNewOrder(name, cost, date, bill)
                            listItem.add(itemNew)
                        }
                    }
                    itemRecyclerView.adapter = ItemConfirmOrderAdapter(listItem)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

}
