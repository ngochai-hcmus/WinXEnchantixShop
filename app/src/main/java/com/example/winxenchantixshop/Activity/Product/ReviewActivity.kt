package com.example.winxenchantixshop.Activity.Product

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Adapter.ItemNewOrderCustomerAdapter
import com.example.winxenchantixshop.Adapter.ReviewAdapter
import com.example.winxenchantixshop.DTO.*
import com.example.winxenchantixshop.databinding.ActivityReviewBinding
import com.google.firebase.database.*

class ReviewActivity : AppCompatActivity() {
    private lateinit var binding : ActivityReviewBinding
    private lateinit var itemAdapter : ReviewAdapter
    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var db_ref : DatabaseReference
    private lateinit var listItem: ArrayList<Product>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val billId = intent.getStringExtra("billID").toString()
        val currentUser = User.Email("")!!.dropLast(10)

        itemRecyclerView = binding.recycleReviewProduct
        itemRecyclerView.layoutManager = LinearLayoutManager(this)
        itemRecyclerView.setHasFixedSize(true)
        listItem = arrayListOf<Product>()
        itemAdapter = ReviewAdapter(listItem)
        getListProduct(billId)

    }

    private fun getListProduct(billID: String) {
        db_ref = FirebaseDatabase.getInstance().getReference("HistoryOrder").child(billID).child("List")
        db_ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        val p = i.getValue(Product::class.java)
                        listItem.add(p!!)
                    }
                    println(listItem.toString())
                }
                itemRecyclerView.adapter = ReviewAdapter(listItem)
            }


            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Not Existed", Toast.LENGTH_LONG).show()
            }
        })
    }
}