package com.example.winxenchantixshop.Activity.Product

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Adapter.ItemNewOrderCustomerAdapter
import com.example.winxenchantixshop.Adapter.MyReviewAdapter
import com.example.winxenchantixshop.Adapter.ReviewAdapter
import com.example.winxenchantixshop.DTO.*
import com.example.winxenchantixshop.databinding.ActivityListReviewBinding
import com.example.winxenchantixshop.databinding.ActivityReviewBinding
import com.google.firebase.database.*

class ReviewListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListReviewBinding
    private lateinit var itemAdapter : MyReviewAdapter
    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var db_ref : DatabaseReference
    private lateinit var listItem: ArrayList<Review>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val billId = intent.getStringExtra("billID").toString()
        val currentUser = User.Email("")!!.dropLast(10)

        itemRecyclerView = binding.recycleViewListProduct
        itemRecyclerView.layoutManager = LinearLayoutManager(this)
        itemRecyclerView.setHasFixedSize(true)
        listItem = arrayListOf<Review>()
        itemAdapter = MyReviewAdapter(listItem)

        getListReview(currentUser)

    }

    private fun getListReview(uid: String) {
        db_ref = FirebaseDatabase.getInstance().getReference("Review")
        db_ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        for (j in i.children) {
                            if (j.key!!.drop(14) == uid){
                                val item = j.getValue(Review::class.java)
                                listItem.add(0, item!!)
                            }
                        }
                    }
                }
                println(listItem.toString())
                itemRecyclerView.adapter = MyReviewAdapter(listItem)
            }


            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Not Existed", Toast.LENGTH_LONG).show()
            }
        })
    }
}