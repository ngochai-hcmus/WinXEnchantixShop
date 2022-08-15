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
import com.example.winxenchantixshop.Adapter.ChatSellerAdapter
import com.example.winxenchantixshop.DTO.Message
import com.example.winxenchantixshop.R
import com.google.firebase.database.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


class ChatSellerActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var database2 : DatabaseReference
    private lateinit var sendimg : ImageView
    private lateinit var mess : TextView
    private lateinit var ChatRecyclerview: RecyclerView
    var chatList = ArrayList<Message>()
    private lateinit var emai : String

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val email = intent.getStringExtra("UserEmail").toString()
        val usermail = email
        val MyEmail = intent.getStringExtra("MyEmail").toString()
        Toast.makeText(this, "${usermail}", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "${MyEmail}", Toast.LENGTH_SHORT).show()
        ChatRecyclerview = findViewById(R.id.recycle_chat)
        ChatRecyclerview.layoutManager = LinearLayoutManager(this)
        ChatRecyclerview.setHasFixedSize(true)


        sendimg = findViewById(R.id.img_send)
        sendimg.setOnClickListener{

            mess = findViewById(R.id.txt_input)
            val message = mess.text.toString().trim()
            if(message.isEmpty()){
                Toast.makeText(this, "Message is empty!!!", Toast.LENGTH_SHORT).show()
            }
            else
            {
                create_mess(message,MyEmail,MyEmail,usermail)
            }
            mess.editableText.clear()

        }

        readMessage(MyEmail,usermail)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun create_mess(message: String, sellername: String, email: String, userEmail: String) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = current.format(formatter)
        val time = formatted.toString()
        val  seller_message = Message(message,time,sellername,email)
        sendMessage(seller_message,email,time,userEmail)
    }

    private fun sendMessage(userMessage: Message, email: String, time: String, userEmail: String) {

        database = FirebaseDatabase.getInstance().getReference("Chat").child(userEmail)
        database.child("${time}:time").setValue(userMessage)

    }
    private fun readMessage(email: String, usermail: String){
        database2  = FirebaseDatabase.getInstance().getReference("Chat").child(usermail)
        database2.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){

                    for (chatSnapshot in snapshot.children){

                        val chat = chatSnapshot.getValue(Message::class.java)
                        if (chat != null) {
                            chatList.add(chat)
                        }
                    }
                }
                    ChatRecyclerview.adapter = ChatSellerAdapter(chatList,usermail)
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}