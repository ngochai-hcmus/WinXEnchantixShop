package com.example.winxenchantixshop.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Activity.Chat.ChatActivity
import com.example.winxenchantixshop.Activity.Chat.ChatListActivity
import com.example.winxenchantixshop.Activity.Chat.ChatSellerActivity
import com.example.winxenchantixshop.Adapter.ChatListAdapter.*
import com.example.winxenchantixshop.DTO.Chat
import com.example.winxenchantixshop.R

class ChatListAdapter(private val context: Context,private val ChatList: ArrayList<String>, private var Myemail: String):
    RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val email = ChatList[position]
        holder.useremail.text = email.toString()
        holder.layoutchat.setOnClickListener {
            val intent = Intent(context ,ChatSellerActivity::class.java)
            intent.putExtra("UserEmail",email)
            intent.putExtra("MyEmail",Myemail)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return ChatList.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val useremail : TextView = view.findViewById(R.id.text_name)
        val layoutchat: ImageView = view.findViewById(R.id.img_ava)
    }

}