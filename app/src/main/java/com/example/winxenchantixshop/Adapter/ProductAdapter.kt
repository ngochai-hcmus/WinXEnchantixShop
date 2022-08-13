package com.example.winxenchantixshop.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.PixelCopy
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Activity.Product.EditProductActivity
import com.example.winxenchantixshop.Activity.Product.ProductInformationActivity
import com.example.winxenchantixshop.DTO.Product
import com.example.winxenchantixshop.DTO.User
import com.example.winxenchantixshop.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class ProductAdapter(private var listProduct : ArrayList<Product>) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    private lateinit var db_ref : DatabaseReference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listProduct[position]

        Picasso.get().load(currentItem.imageUrl).into(holder.imageProduct)

        holder.productName.text = currentItem.productName
        holder.price.text = currentItem.price
        holder.describe.text = currentItem.desciption

        val imageProduct = currentItem.imageUrl
        val category = currentItem.category
        val amount = currentItem.amount

        db_ref = FirebaseDatabase.getInstance().getReference("Account")

        val currentUser = User.Email("")!!.dropLast(10)

        var type = ""
        db_ref.child(currentUser).child("type").get().addOnSuccessListener {
            type = it.value.toString()
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

        db_ref = FirebaseDatabase.getInstance().getReference("Product")

        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v:View?) {
                if(type == "customer"){
                    val activity = v!!.context as AppCompatActivity
                    val intent = Intent(v.context,ProductInformationActivity::class.java)

                    intent.putExtra("imageUrl", imageProduct)
                    intent.putExtra("productName", holder.productName.text)
                    intent.putExtra("category", category)
                    intent.putExtra("price", holder.price.text)
                    intent.putExtra("amount", amount)
                    intent.putExtra("description", holder.describe.text)
                    activity.startActivity(intent)

                }else{
                    val activity = v!!.context as AppCompatActivity
                    val intent = Intent(v.context,EditProductActivity::class.java)

                    intent.putExtra("imageUrl", imageProduct)
                    intent.putExtra("productName", holder.productName.text)
                    intent.putExtra("category", category)
                    intent.putExtra("price", holder.price.text)
                    intent.putExtra("amount", amount)
                    intent.putExtra("description", holder.describe.text)

                    activity.startActivity(intent)

                }
            }
        })


    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    fun updateProductList(listProduct : List<Product>){

        this.listProduct.clear()
        this.listProduct.addAll(listProduct)
        notifyDataSetChanged()

    }


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val imageProduct : ImageView = itemView.findViewById(R.id.img_product)
        val productName : TextView = itemView.findViewById(R.id.text_name)
        val price : TextView = itemView.findViewById(R.id.text_price)
        val describe : TextView = itemView.findViewById(R.id.text_describe)


    }


}