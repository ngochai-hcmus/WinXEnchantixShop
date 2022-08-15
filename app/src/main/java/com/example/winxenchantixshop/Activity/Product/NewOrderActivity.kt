package com.example.winxenchantixshop.Activity.Product

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Adapter.ItemAdapter
import com.example.winxenchantixshop.Adapter.ItemNewOrderAdapter
import com.example.winxenchantixshop.Adapter.ItemNewOrderCustomerAdapter
import com.example.winxenchantixshop.Adapter.NoticeCustomerAdapter
import com.example.winxenchantixshop.DTO.ClientInfo
import com.example.winxenchantixshop.DTO.ItemNewOrder
import com.example.winxenchantixshop.DTO.NoticeCustomer
import com.example.winxenchantixshop.DTO.User
import com.example.winxenchantixshop.databinding.ActivityNewOrderBinding
import com.google.firebase.database.*


class NewOrderActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNewOrderBinding
    private lateinit var itemAdapter : ItemNewOrderCustomerAdapter
    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var db_ref : DatabaseReference
    private lateinit var listItem: ArrayList<ItemNewOrder>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentUser = User.Email("")!!.dropLast(10)
        itemRecyclerView = binding.recycleViewListProduct
        itemRecyclerView.layoutManager = LinearLayoutManager(this)
        itemRecyclerView.setHasFixedSize(true)
        listItem = arrayListOf<ItemNewOrder>()
        itemAdapter = ItemNewOrderCustomerAdapter(listItem)
        getList(currentUser)

    }

    private fun getList(name: String) {
        db_ref = FirebaseDatabase.getInstance().getReference("WaitingOrder")
        db_ref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        val trim = i.key!!.toString().drop(14)
                        if (trim == name) {
                            val item = i.child("ClientInfo").getValue(ClientInfo::class.java)

                            val fname = item?.name
                            val cost = item?.Cost
                            val date = item?.Time
                            val bill = i.key.toString()

                            val itemNew = ItemNewOrder(fname, cost, date, bill)
                            listItem.add(0, itemNew)
                        }
                    }
                    itemRecyclerView.adapter = ItemNewOrderCustomerAdapter(listItem)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Not Existed", Toast.LENGTH_LONG).show()
            }
        })
    }

}
