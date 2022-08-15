package com.example.winxenchantixshop.Activity.AdminSeller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Adapter.ItemAdapter
import com.example.winxenchantixshop.Adapter.ItemHistoryOrderAdapter
import com.example.winxenchantixshop.Adapter.ItemNewOrderAdapter
import com.example.winxenchantixshop.DTO.ClientInfo
import com.example.winxenchantixshop.DTO.ItemNewOrder
import com.example.winxenchantixshop.DTO.Product
import com.example.winxenchantixshop.databinding.ActivityOrderHistoryBinding
import com.google.firebase.database.*

class OrderHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderHistoryBinding
    private lateinit var db_ref : DatabaseReference
    private lateinit var itemHistoryOrderAdapter : ItemHistoryOrderAdapter
    private lateinit var itemHistoryOrderRecyclerView: RecyclerView
    private lateinit var listItem : ArrayList<ItemNewOrder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemHistoryOrderRecyclerView = binding.recycleViewListProduct
        itemHistoryOrderRecyclerView.layoutManager = LinearLayoutManager(this)
        itemHistoryOrderRecyclerView.setHasFixedSize(true)

        listItem = arrayListOf<ItemNewOrder>()
        itemHistoryOrderAdapter = ItemHistoryOrderAdapter(listItem)
        getItem()


    }

    private fun getItem() {
        db_ref = FirebaseDatabase.getInstance().getReference("HistoryOrder")

        db_ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val item = i.child("ClientInfo").getValue(ClientInfo::class.java)

                        val name = item!!.name
                        val cost = item!!.Cost
                        val date = item!!.Time

                        val itemNew = ItemNewOrder(name, cost, date, "")
                        listItem.add(0, itemNew)

                    }
                    itemHistoryOrderRecyclerView.adapter = ItemHistoryOrderAdapter(listItem)
                }

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }
}