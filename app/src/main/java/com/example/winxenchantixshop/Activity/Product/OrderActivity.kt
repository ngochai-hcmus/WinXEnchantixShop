package com.example.winxenchantixshop.Activity.Product

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Adapter.ItemAdapter
import com.example.winxenchantixshop.Adapter.ProductAdapter
import com.example.winxenchantixshop.DAO.AccountDAO
import com.example.winxenchantixshop.DTO.Account
import com.example.winxenchantixshop.DTO.Product
import com.example.winxenchantixshop.DTO.ProductView
import com.example.winxenchantixshop.DTO.User
import com.example.winxenchantixshop.R
import com.example.winxenchantixshop.databinding.ActivityOrderBinding
import com.example.winxenchantixshop.databinding.ActivityProductInformationBinding
import com.google.firebase.database.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class OrderActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOrderBinding
    private lateinit var db_ref : DatabaseReference
    private lateinit var temp_listUser: ArrayList<Account>
    private lateinit var productAdapter : ItemAdapter
    private lateinit var itemRecyclerView: RecyclerView


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val currentUser = User.Email("")
        getUser(currentUser.toString())

        binding.btnNext.setOnClickListener{
            val intent = Intent(this, ShippingAddressActivity::class.java)
            startActivity(intent)
        }

        val listProduct = intent.getSerializableExtra("listProduct") as ArrayList<Product>

        itemRecyclerView = binding.recycleViewListProduct
        itemRecyclerView.layoutManager = LinearLayoutManager(this)
        itemRecyclerView.setHasFixedSize(true)


        productAdapter = ItemAdapter(listProduct)
        itemRecyclerView.adapter = productAdapter

        var billCost : Int = 0
        for (i in listProduct){
            billCost += (i.price!!.toInt()?:0) * (i.amount!!.toInt()?:0)
        }
        val ship : Int = 30000
        billCost += ship

        binding.inputTotalCost.text = billCost.toString()
        binding.inputTransposeFee.text = ship.toString()


        binding.btnOder.setOnClickListener {
            val currentTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val formatBill = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
            val time = currentTime.format(formatter)

            val username =  binding.inputUserName.text
            val phone = binding.inputPhone.text
            val location = binding.inputLocation.text

            db_ref = FirebaseDatabase.getInstance().getReference("WaitingOrder")
                .child("${currentTime.format(formatBill)}${currentUser!!.dropLast(10)}")
            for (i in listProduct) {
                db_ref.child("List").child(i.productName.toString()).setValue(i)
            }

            db_ref.child("ClientInfo").child("Time").setValue(time)
            db_ref.child("ClientInfo").child("Cost").setValue(billCost.toString())
            db_ref.child("ClientInfo").child("name").setValue(username)
            db_ref.child("ClientInfo").child("phone").setValue(phone)
            db_ref.child("ClientInfo").child("location").setValue(location)
            db_ref.child("ClientInfo").child("UserID").setValue(currentUser!!.dropLast(10))


            intent = Intent(this, SuccessfulOrderActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        val currentUser = User.Email("")
        getUser(currentUser.toString())
    }

//    private fun getUserList() {
//        db_ref = FirebaseDatabase.getInstance().getReference("Account")
//
//        db_ref.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//                if (snapshot.exists()) {
//                    for (productSnapshot in snapshot.children) {
//                        val account = productSnapshot.getValue(Account::class.java)
//                        listUser.add(account!!)
//                    }
//
//                }
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//
//        })
//    }

    private fun getUser(name: String) {
        temp_listUser = arrayListOf<Account>()
        db_ref = FirebaseDatabase.getInstance().getReference("Account")
        db_ref.addValueEventListener(object:ValueEventListener{
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