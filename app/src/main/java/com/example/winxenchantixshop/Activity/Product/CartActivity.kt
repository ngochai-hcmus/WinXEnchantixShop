package com.example.winxenchantixshop.Activity.Product

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Activity.Util.SwipeToDeleteCallback
import com.example.winxenchantixshop.Adapter.ItemAdapter
import com.example.winxenchantixshop.Adapter.ProductAdapter
import com.example.winxenchantixshop.DTO.Account
import com.example.winxenchantixshop.DTO.Product
import com.example.winxenchantixshop.DTO.User
import com.example.winxenchantixshop.databinding.ActivityCartBinding
import com.google.firebase.database.*
import java.io.Serializable


class CartActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCartBinding
    private lateinit var productAdapter : ItemAdapter
    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var db_ref : DatabaseReference
    private lateinit var listProduct: ArrayList<Product>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userName = User.Email("")!!.dropLast(10)

        itemRecyclerView = binding.cycleviewGioHang
        itemRecyclerView.layoutManager = LinearLayoutManager(this)
        itemRecyclerView.setHasFixedSize(true)
        listProduct = arrayListOf<Product>()
        productAdapter = ItemAdapter(listProduct)
        getUserProduct(userName)

        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos : Int = viewHolder.adapterPosition
                val productToDelete : Product = listProduct.get(pos)
                val productName = productToDelete.productName.toString()
                deleteUserProduct(userName, productName)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(itemRecyclerView)

        binding.buttonOrder.setOnClickListener {
            intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("listProduct", listProduct)
            startActivity(intent)
        }

    }


    private fun getUserProduct(owner: String) {
        listProduct = arrayListOf<Product>()
        db_ref = FirebaseDatabase.getInstance().getReference("Cart").child(owner)
        db_ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (productSnapshot in snapshot.children) {
                        val product = productSnapshot.getValue(Product::class.java)
                        listProduct.add(product!!)
                    }
                    itemRecyclerView.adapter = ItemAdapter(listProduct)
                    var billCost = 0
                    for (i in listProduct) {
                        billCost += (i.price!!.toInt()?:0) * (i.amount!!.toInt()?:0)
                    }
                    binding.billCost.text = billCost.toString()
                }
                else{
                    itemRecyclerView.adapter = ItemAdapter(listProduct)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun deleteUserProduct(owner: String, productName: String) {
        listProduct = arrayListOf<Product>()
        db_ref = FirebaseDatabase.getInstance().getReference("Cart").child(owner)

        db_ref.child(productName).removeValue().addOnSuccessListener {
            Toast.makeText(applicationContext, "Deleted 1 item", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(applicationContext, "Failed to delete, try again", Toast.LENGTH_LONG).show()
        }
    }

}
