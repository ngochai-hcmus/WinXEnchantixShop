package com.example.winxenchantixshop.Activity.Product

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Adapter.ItemAdapter
import com.example.winxenchantixshop.DTO.*
import com.example.winxenchantixshop.databinding.ActivityNewOderDetailBinding
import com.example.winxenchantixshop.databinding.ActivityOrderConfirmedBinding
import com.google.firebase.database.*

class OrderConfirmDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOrderConfirmedBinding
    private lateinit var db_ref : DatabaseReference
    private lateinit var temp_listUser: ArrayList<Account>
    private lateinit var productAdapter : ItemAdapter
    private lateinit var itemRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderConfirmedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val currentUser = User.Email("")
        getUser(currentUser.toString())

        val billId = intent.getStringExtra("billID").toString()
        getList(billId)
        val listProduct = ArrayList<Product>()

        itemRecyclerView = binding.recycleViewListProduct
        itemRecyclerView.layoutManager = LinearLayoutManager(this)
        itemRecyclerView.setHasFixedSize(true)


        productAdapter = ItemAdapter(listProduct)
        itemRecyclerView.adapter = productAdapter


    }

    private fun getList(name: String) {
        val listProduct = ArrayList<Product>()
        db_ref = FirebaseDatabase.getInstance().getReference("HistoryOrder")
        db_ref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        if (i.key.toString() == name) {
                            for (j in i.child("List").children) {
                                val item = j.getValue(Product::class.java)
                                listProduct.add(item!!)
                            }
                            val item = i.child("ClientInfo").getValue(ClientInfo::class.java)
                            binding.inputTotalCost.text = item!!.Cost.toString()
                            binding.inputTransposeFee.text = "30000"
                        }
                    }
                    itemRecyclerView.adapter = ItemAdapter(listProduct)
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    override fun onResume() {
        super.onResume()
        val currentUser = User.Email("")
        getUser(currentUser.toString())
    }


    private fun getUser(name: String) {
        temp_listUser = arrayListOf<Account>()
        db_ref = FirebaseDatabase.getInstance().getReference("Account")
        db_ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Checking if the value exists
                if (snapshot.exists()){
                    // looping through the values
                    for (i in snapshot.children){
                        val account = i.getValue(Account::class.java)
                        // checking if the name searched is available and adding it to the array list
                        if (account!!.email.toString() == name){
                            temp_listUser.add(account)
                        }
                    }
                    binding.inputUserName.text = temp_listUser[0].name
                    binding.inputPhone.text = temp_listUser[0].phone
                    val location = "${temp_listUser[0].house}, ${temp_listUser[0].country}, ${temp_listUser[0].city}"
                    if (location != ", , ") {
                        binding.inputLocation.text = location
                    }

                }
                else{
                    Toast.makeText(applicationContext, "Not Existed", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

}