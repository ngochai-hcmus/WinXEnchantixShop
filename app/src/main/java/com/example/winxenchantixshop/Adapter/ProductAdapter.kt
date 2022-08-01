package com.example.winxenchantixshop.Adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.PixelCopy
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.DTO.Product
import com.example.winxenchantixshop.R
import com.squareup.picasso.Picasso

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    private val listProduct = ArrayList<Product>()

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