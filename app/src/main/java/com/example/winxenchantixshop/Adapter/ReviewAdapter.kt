package com.example.winxenchantixshop.Adapter

import android.content.Intent
import android.graphics.Typeface
import android.os.Build
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
import com.example.winxenchantixshop.DTO.User
import com.example.winxenchantixshop.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private lateinit var db_ref : DatabaseReference

class ReviewAdapter(private val listItem : ArrayList<Product>) : RecyclerView.Adapter<ReviewAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_review_product,parent,false)
        return MyViewHolder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listItem[position]

        Picasso.get().load(currentItem.imageUrl).into(holder.imageProduct)
        holder.productName.text = currentItem.productName

        holder.btnReview.setOnClickListener {
            val currentTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val formatReview = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
            val time = currentTime.format(formatter)
            val currentUser = User.Email("")!!.dropLast(10)

            db_ref = FirebaseDatabase.getInstance().getReference("Review")
                    .child(currentItem.productName.toString())
                    .child("${currentTime.format(formatReview)}$currentUser")

            db_ref.child("userID").setValue(currentUser)
            db_ref.child("productName").setValue(currentItem.productName)
            db_ref.child("star").setValue(holder.rBar.rating.toString())
            db_ref.child("comment").setValue(holder.post.text.toString())
            db_ref.child("time").setValue(time.toString())

            Toast.makeText(holder.itemView.context, "Uploaded review", Toast.LENGTH_LONG).show()


        }

    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val productName: TextView = itemView.findViewById(R.id.txt_product_name)
        val imageProduct : ImageView = itemView.findViewById(R.id.img_profile)
        val rBar : RatingBar = itemView.findViewById(R.id.ratingBar)
        val btnReview : AppCompatButton = itemView.findViewById(R.id.btn_review)
        val post : TextInputEditText = itemView.findViewById(R.id.textInput_review)
    }

}