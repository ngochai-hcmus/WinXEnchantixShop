package com.example.winxenchantixshop.Activity.AdminSeller

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Adapter.ItemAdapter
import com.example.winxenchantixshop.DTO.*
import com.example.winxenchantixshop.databinding.ActivityConfirmBinding
import com.google.firebase.database.*

class ConfirmActivity  : AppCompatActivity() {
    private lateinit var binding : ActivityConfirmBinding
    private lateinit var db_ref : DatabaseReference
    private lateinit var listProduct: ArrayList<Product>
    private lateinit var productAdapter : ItemAdapter
    private lateinit var itemRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_product_information)

        super.onCreate(savedInstanceState)
        binding = ActivityConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db_ref = FirebaseDatabase.getInstance().getReference("Cart")

        itemRecyclerView = binding.recycler
        itemRecyclerView.layoutManager = LinearLayoutManager(this)
        itemRecyclerView.setHasFixedSize(true)
        listProduct = ArrayList<Product>()
        productAdapter = ItemAdapter(listProduct)

        val intent = intent

        val billID = intent.getStringExtra("billID").toString()

        db_ref = FirebaseDatabase.getInstance().getReference("WaitingOrder").child(billID)

        db_ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val item = snapshot.child("ClientInfo").getValue(ClientInfo::class.java)

                    val name = item!!.name
                    val phone = item!!.phone
                    val location = item!!.location
                    val cost = item!!.Cost
                    val date = item!!.Time

                    binding.txtDate.text = date.toString()
                    binding.textLocation.text = location.toString()
                    binding.textPhone.text = phone.toString()
                    binding.textUsername.text = name.toString()
                    binding.textTotal.text = cost.toString()


                    val product = snapshot.child("List")
                    for (i in product.children) {
                        val p = i.getValue(Product::class.java)
                        listProduct.add(p!!)
                    }
                    itemRecyclerView.adapter = ItemAdapter(listProduct)
                }

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

        binding.btnConfirm.setOnClickListener {
            db_ref = FirebaseDatabase.getInstance().getReference("WaitingOrder").child(billID)
            db_ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        FirebaseDatabase.getInstance().getReference("HistoryOrder")
                            .child(billID)
                            .setValue(snapshot.getValue())
                        Toast.makeText(applicationContext, "Successful", Toast.LENGTH_LONG).show()
                    }

                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
            db_ref.removeValue()

            db_ref = FirebaseDatabase.getInstance().getReference("WaitingOrder").child(billID)
                    .child("ClientInfo").child("UserID")

            db_ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val uid = snapshot.getValue().toString()
                        val date = binding.txtDate.text.toString()
                        val notice = NoticeCustomer(billID, date, "new")
                        FirebaseDatabase.getInstance().getReference("Notification").child(uid).child(billID).setValue(notice)
                        Toast.makeText(applicationContext, "Successful", Toast.LENGTH_LONG).show()
                    }

                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })


            finish()

        }


    }



}