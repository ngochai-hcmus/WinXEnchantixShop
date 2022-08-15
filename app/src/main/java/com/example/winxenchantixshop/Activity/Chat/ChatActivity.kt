package com.example.winxenchantixshop.Activity.Chat

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Adapter.ChatAdapter
import com.example.winxenchantixshop.DTO.Message
import com.example.winxenchantixshop.DTO.User
import com.example.winxenchantixshop.R
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.time.format.DateTimeFormatter

import java.time.LocalDateTime
import java.time.format.FormatStyle
import android.widget.Toast.makeText as makeText1


class ChatActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var database2 : DatabaseReference

    private lateinit var sendimg : ImageView
    private lateinit var mess : TextView
    private lateinit var reference: DatabaseReference
    private lateinit var ChatRecyclerview: RecyclerView
    var chatList = ArrayList<Message>()
    private lateinit var emai : String
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val currentUser = User.Email("")
        val Email = currentUser!!.dropLast(10)

        println(Email)

        val usermail : String
        usermail = Email
        val user2: String
        user2 = "Thai1"
        ChatRecyclerview = findViewById(R.id.recycle_chat)
        ChatRecyclerview.layoutManager = LinearLayoutManager(this)
        ChatRecyclerview.setHasFixedSize(true)


        sendimg = findViewById(R.id.img_send)
        sendimg.setOnClickListener{

            mess = findViewById(R.id.txt_input)
            val message = mess.text.toString().trim()
            if(message.isEmpty()){
                makeText1(this, "Message is empty!!!", Toast.LENGTH_SHORT).show()
            }
            else
            {
                create_mess(message,usermail,usermail)
            }
            mess.editableText.clear()

        }


        readMessage(usermail,usermail)

    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun create_mess(message: String, username: String, email: String) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = current.format(formatter)
        val time = formatted.toString()
        val  user_message = Message(message,time,username,email)

        sendMessage(user_message,email,time)


    }

    private fun sendMessage(userMessage: Message, email: String, time: String) {

        var reference = FirebaseDatabase.getInstance().getReference("Chat").child(email)
        reference!!.child("${time}:time").setValue(userMessage)


    }
    private fun readMessage(email: String, usermail: String){
        database2  = FirebaseDatabase.getInstance().getReference("Chat").child(email)
        database2.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                chatList.clear()
                if(snapshot.exists()){

                    for (chatSnapshot in snapshot.children){

                        val chat = chatSnapshot.getValue(Message::class.java)
                        if (chat != null) {
                            chatList.add(chat)
                        }


                    }
                }

                ChatRecyclerview.adapter = ChatAdapter(chatList,usermail)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}