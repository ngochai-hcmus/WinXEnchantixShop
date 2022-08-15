package com.example.winxenchantixshop.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Activity.Product.ProductInformationActivity
import com.example.winxenchantixshop.DTO.Account
import com.example.winxenchantixshop.R


class AccountAdapter(private val AccountList: ArrayList<Account>) : RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_seller,
            parent,false
        )
        return AccountViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val currentitem = AccountList[position]

        holder.Account_Name.text = currentitem.name
        holder.Account_Email.text = currentitem.email
        holder.Account_Phone.text = currentitem.phone
    }

    override fun getItemCount(): Int {
        return AccountList.size
    }

    fun updateAccountList(AccountList: List<Account>){

        this.AccountList.clear()
        this.AccountList.addAll(AccountList)
        notifyDataSetChanged()
    }

    class AccountViewHolder(itemview:View) : RecyclerView.ViewHolder(itemview){

        val Account_Name : TextView = itemview.findViewById(R.id.Name)
        val Account_Email : TextView = itemview.findViewById(R.id.Email)
        val Account_Phone : TextView = itemview.findViewById(R.id.Phone)


    }
}