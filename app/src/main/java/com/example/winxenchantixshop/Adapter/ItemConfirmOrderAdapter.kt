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
import com.example.winxenchantixshop.Activity.Product.NewOrderDetailActivity
import com.example.winxenchantixshop.Activity.Product.OrderConfirmDetailActivity
import com.example.winxenchantixshop.Activity.Product.ProductInformationActivity
import com.example.winxenchantixshop.DTO.ItemNewOrder
import com.example.winxenchantixshop.DTO.Product
import com.example.winxenchantixshop.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.NonDisposableHandle.parent

class ItemConfirmOrderAdapter(private val listItem : ArrayList<ItemNewOrder>) : RecyclerView.Adapter<ItemConfirmOrderAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_notify,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listItem[position]

        holder.name.text = currentItem.name
        holder.cost.text = currentItem.cost
        holder.date.text = currentItem.date
        holder.bill.text = currentItem.bill

        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v:View?) {
                val activity = v!!.context as AppCompatActivity
                val intent = Intent(v.context, OrderConfirmDetailActivity::class.java)

                intent.putExtra("billID", holder.bill.text.toString())

                activity.startActivity(intent)

            }
        })

    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.input_user_name)
        val cost: TextView = itemView.findViewById(R.id.input_total_cost)
        val date: TextView = itemView.findViewById(R.id.input_date)
        val bill: TextView = itemView.findViewById(R.id.input_bill)

    }

}