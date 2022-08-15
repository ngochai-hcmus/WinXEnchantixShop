package com.example.winxenchantixshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.DTO.Message
import com.example.winxenchantixshop.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ChatAdapter( private val chatList: ArrayList<Message>,usermail: String):
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {


    private val MESSAGE_TYPE_LEFT = 0
    private val MESSAGE_TYPE_RIGHT = 1
    private val MyEmail = usermail.toString()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {

       if(viewType == MESSAGE_TYPE_RIGHT){
           val view = LayoutInflater.from(parent.context).inflate(R.layout.item_send,parent,false)
           return ChatViewHolder(view)
        }
        else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_receive,parent,false)
            return ChatViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chatList[position]
        holder.mess.text = chat.user_message
        holder.time.text = chat.time
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (chatList[position].email.toString() != MyEmail){
            return MESSAGE_TYPE_LEFT
        }
        else
        {
            return MESSAGE_TYPE_RIGHT
        }
    }



    class ChatViewHolder(view: View): RecyclerView.ViewHolder(view){

        val mess: TextView = view.findViewById(R.id.txt_mess)
        val time: TextView = view.findViewById(R.id.txt_time)

    }


}
