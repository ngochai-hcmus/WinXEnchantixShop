package com.example.winxenchantixshop.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.PixelCopy
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Activity.Product.ProductInformationActivity
import com.example.winxenchantixshop.DTO.Product
import com.example.winxenchantixshop.R
import com.squareup.picasso.Picasso

class ItemAdapter(private val listProduct : ArrayList<Product>) : RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_payment_order,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listProduct[position]

        val price = currentItem.price?.toInt()
        val amount = currentItem.amount?.toInt()
        val totalPriceItem : Int = (price?:0) * (amount?:0)

        Picasso.get().load(currentItem.imageUrl).into(holder.imageProduct)

        holder.productName.text = currentItem.productName
        holder.price.text = currentItem.price
//        holder.description.text = currentItem.desciption
        holder.amount.text =  currentItem.amount

        holder.totalPrice.text = "total : " + totalPriceItem
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    fun getData(): ArrayList<Product> {
        return listProduct
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val imageProduct : ImageView = itemView.findViewById(R.id.img_product)
        val productName : TextView = itemView.findViewById(R.id.text_name)
        val price : TextView = itemView.findViewById(R.id.text_price)
//        val description : TextView = itemView.findViewById(R.id.text_describe)
        val amount : TextView = itemView.findViewById(R.id.text_amount)
        val totalPrice : TextView = itemView.findViewById(R.id.text_total)
    }


}