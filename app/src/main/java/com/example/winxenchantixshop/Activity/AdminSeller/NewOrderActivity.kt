package com.example.winxenchantixshop.Activity.AdminSeller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Adapter.ItemAdapter
import com.example.winxenchantixshop.Adapter.ItemNewOrderAdapter
import com.example.winxenchantixshop.Adapter.ProductAdapter
import com.example.winxenchantixshop.DTO.*
import com.example.winxenchantixshop.databinding.ActivityNewOrderBinding
import com.example.winxenchantixshop.databinding.ItemNotifyBinding
import com.google.firebase.database.*
import kotlinx.coroutines.NonCancellable.children

class NewOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewOrderBinding
    private lateinit var db_ref : DatabaseReference
    private lateinit var itemNewOrderAdapter : ItemNewOrderAdapter
    private lateinit var itemNewOrderRecyclerView: RecyclerView
    private lateinit var listItem : ArrayList<ItemNewOrder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)


        itemNewOrderRecyclerView = binding.recycleViewListProduct
        itemNewOrderRecyclerView.layoutManager = LinearLayoutManager(this)
        itemNewOrderRecyclerView.setHasFixedSize(true)

        listItem = arrayListOf<ItemNewOrder>()
        itemNewOrderAdapter = ItemNewOrderAdapter(listItem)
        getItem()
    }

    private fun getItem() {
        db_ref = FirebaseDatabase.getInstance().getReference("WaitingOrder")

        db_ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listItem.clear()
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val item = i.child("ClientInfo").getValue(ClientInfo::class.java)

                        val name = item!!.name
                        val cost = item!!.Cost
                        val date = item!!.Time
                        val bill = i.key.toString()

                        val itemNew = ItemNewOrder(name, cost, date, bill)
                        listItem.add(0, itemNew)

                    }
                    itemNewOrderRecyclerView.adapter?.notifyDataSetChanged()
                    itemNewOrderRecyclerView.adapter = ItemNewOrderAdapter(listItem)

                }
                else {
                    itemNewOrderRecyclerView.adapter = ItemNewOrderAdapter(listItem)
                }

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }

    override fun onResume() {
        super.onResume()
        getItem()
    }

}