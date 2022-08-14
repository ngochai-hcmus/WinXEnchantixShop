package com.example.winxenchantixshop.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Activity.AdminSeller.ConfirmActivity
import com.example.winxenchantixshop.Activity.Product.EditProductActivity
import com.example.winxenchantixshop.Activity.Product.ProductInformationActivity
import com.example.winxenchantixshop.DTO.ItemNewOrder
import com.example.winxenchantixshop.DTO.Product
import com.example.winxenchantixshop.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.NonDisposableHandle.parent

class ItemHistoryOrderAdapter(private val listItem : ArrayList<ItemNewOrder>) : RecyclerView.Adapter<ItemHistoryOrderAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_order_history,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listItem[position]

        holder.name.text = currentItem.name
        holder.cost.text = currentItem.cost
        holder.date.text = currentItem.date

    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.text_order_info1)
        val cost: TextView = itemView.findViewById(R.id.input_total_cost)
        val date: TextView = itemView.findViewById(R.id.input_date)

    }

}