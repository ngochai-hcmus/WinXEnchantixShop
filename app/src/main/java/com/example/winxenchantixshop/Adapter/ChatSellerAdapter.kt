package com.example.winxenchantixshop.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.DTO.Message
import com.example.winxenchantixshop.R

class ChatSellerAdapter(private val chatList: ArrayList<Message>, usermail: String) : RecyclerView.Adapter<ChatSellerAdapter.ChatSellerViewHolder>(){

    private val MESSAGE_TYPE_LEFT = 0
    private val MESSAGE_TYPE_RIGHT = 1
    private var UserEmail = usermail
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatSellerViewHolder {
        if(viewType == MESSAGE_TYPE_LEFT){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_receive,parent,false)
            return ChatSellerViewHolder(view)
        }
        else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_send,parent,false)
            return ChatSellerViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: ChatSellerViewHolder, position: Int) {
        val chat = chatList[position]
        holder.mess.text = chat.user_message
        holder.time.text = chat.time
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(chatList[position].email.toString() != UserEmail) {
            MESSAGE_TYPE_RIGHT
        } else {
            MESSAGE_TYPE_LEFT
        }
    }
    class ChatSellerViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val mess: TextView = view.findViewById(R.id.txt_mess)
        val time: TextView = view.findViewById(R.id.txt_time)
    }

}