package com.example.winxenchantixshop.Adapter

import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Activity.Product.OrderConfirmDetailActivity
import com.example.winxenchantixshop.DTO.NoticeCustomer
import com.example.winxenchantixshop.DTO.Product
import com.example.winxenchantixshop.DTO.Review
import com.example.winxenchantixshop.DTO.User
import com.example.winxenchantixshop.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private lateinit var db_ref : DatabaseReference

class MyReviewAdapter(private val listItem : ArrayList<Review>) : RecyclerView.Adapter<MyReviewAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_your_review,parent,false)
        return MyViewHolder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listItem[position]

//        Picasso.get().load(currentItem.imageUrl).into(holder.imageProduct)
        holder.productName.text = currentItem.productName
        holder.rev.text = currentItem.comment
        holder.ratingBar.rating = currentItem.star.toString().toFloat()
        holder.tine.text = currentItem.time
        holder.name.text = currentItem.userID
        FirebaseDatabase.getInstance().getReference("Product")
            .child(currentItem.productName.toString())
            .child("imageUrl").get().addOnSuccessListener {
                Picasso.get().load(it.value.toString()).into(holder.imageProduct)
            }


    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.text_name)
        val ratingBar : RatingBar = itemView.findViewById(R.id.ratingBar)
        val name : TextView = itemView.findViewById(R.id.txt_username)
        val rev : TextView = itemView.findViewById(R.id.txt_review)
        val tine : TextView = itemView.findViewById(R.id.txt_datetime)
        val imageProduct : ImageView = itemView.findViewById(R.id.img_product)
        val imageUser : ImageView = itemView.findViewById(R.id.img_profile)


    }

}