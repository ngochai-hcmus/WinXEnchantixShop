package com.example.winxenchantixshop.Adapter

import android.content.Intent
import android.graphics.Typeface
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
import com.example.winxenchantixshop.DTO.NoticeCustomer
import com.example.winxenchantixshop.DTO.Product
import com.example.winxenchantixshop.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.NonDisposableHandle.parent

class NoticeCustomerAdapter(private val listItem : ArrayList<NoticeCustomer>) : RecyclerView.Adapter<NoticeCustomerAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_notify_customer,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listItem[position]

        holder.billID.text = currentItem.billID
        holder.date.text = currentItem.date
        if(currentItem.status == "new") {
            holder.cap.setTypeface(null, Typeface.BOLD)
        }

//        holder.itemView.setOnClickListener(object :View.OnClickListener{
//            override fun onClick(v:View?) {
//                val activity = v!!.context as AppCompatActivity
//                val intent = Intent(v.context, ConfirmActivity::class.java)
//
//                intent.putExtra("billID", holder.bill.text.toString())
//
//                activity.startActivity(intent)
//
//            }
//        })

    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val billID: TextView = itemView.findViewById(R.id.input_ID)
        val date: TextView = itemView.findViewById(R.id.input_date)
        val cap: TextView = itemView.findViewById(R.id.text_order_info1)


    }

}