package com.example.winxenchantixshop.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.DTO.Product
import com.example.winxenchantixshop.DTO.Review
import com.example.winxenchantixshop.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

private lateinit var db_ref : DatabaseReference

class ProductReviewAdapter(private val listItem : ArrayList<Review>) : RecyclerView.Adapter<ProductReviewAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_review,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listItem[position]

        holder.username.text = currentItem.userID
        holder.date.text = currentItem.time
        holder.post.text = currentItem.comment
        holder.rBar.rating = (currentItem.star?.toFloat() ?: 1) as Float

    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val username: TextView = itemView.findViewById(R.id.txt_username)
        val date: TextView = itemView.findViewById(R.id.txt_datetime)
        val rBar : RatingBar = itemView.findViewById(R.id.ratingBar)
        val post : TextView = itemView.findViewById(R.id.txt_cmt)
    }

}