package com.example.winxenchantixshop.Activity.Product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Adapter.ProductAdapter
import com.example.winxenchantixshop.DTO.Product
import com.example.winxenchantixshop.DTO.ProductView

import com.example.winxenchantixshop.databinding.ActivitySearchBinding
import com.google.android.gms.common.util.ArrayUtils.contains
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList


class SearchActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySearchBinding

    private lateinit var productRecyclerView: RecyclerView
    private lateinit var db_ref : DatabaseReference
    private lateinit var listProduct: ArrayList<Product>


//    private object COMPARATOR: DiffUtil.ItemCallback<Product>(){
//        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
//            return oldItem == newItem
//        }
//
//        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
//            return oldItem.productName == newItem.productName
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val kw = intent.getStringExtra(EXTRA_MESSAGE)
        println(kw)
        if (kw != null){
            searchByName(kw)
        }

        binding.btnSearch.setOnClickListener {
            val kw = binding.searchView.getText().toString()
            searchByName(kw)
        }

//        binding.searchView.setOnEditorActionListener { _, actionId, _ ->
//            if (actionId == EditorInfo.IME_ACTION_GO) {
//                val kw = binding.searchView.getText().toString()
//                searchByName(kw)
//            }
//            true
//        }


        productRecyclerView = binding.recycleViewListProduct
        productRecyclerView.layoutManager = LinearLayoutManager(this)
        productRecyclerView.setHasFixedSize(true)
        listProduct = arrayListOf<Product>()
        getProduct()

    }

    private fun getProduct() {
        db_ref = FirebaseDatabase.getInstance().getReference("Product")

        db_ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (productSnapshot in snapshot.children) {
                        val product = productSnapshot.getValue(Product::class.java)
                        listProduct.add(product!!)
                    }
                    productRecyclerView.adapter = ProductAdapter(listProduct)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }

    private fun searchByName(name: String) {
        db_ref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                // Checking if the value exists
                if (snapshot.exists()){
                    listProduct.clear()
                    // looping through the values
                    for (i in snapshot.children){
                        val product = i.getValue(Product::class.java)
                        // checking if the name searched is available and adding it to the array list
                        if (product!!.productName!!.lowercase().contains(name.lowercase())){
                            listProduct.add(product)
                        }
                    }
                    productRecyclerView.adapter = ProductAdapter(listProduct)

                } else{
                    Toast.makeText(applicationContext, "Not Existed", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}
