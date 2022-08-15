package com.example.winxenchantixshop.Activity.Chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Adapter.ChatListAdapter
import com.example.winxenchantixshop.R
import com.google.firebase.database.*

class ChatListActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var ChatListRecyclerview: RecyclerView
    private lateinit var chatList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_chat)

        ChatListRecyclerview = findViewById(R.id.recycleView_list_chat)
        ChatListRecyclerview.layoutManager = LinearLayoutManager(this)
        ChatListRecyclerview.setHasFixedSize(true)

        var myEmail = intent.getStringExtra("email")
        chatList = arrayListOf<String>()
        getChatData(myEmail)

    }

    private fun getChatData(myEmail: String?) {
        database = FirebaseDatabase.getInstance().getReference("Chat")
        database.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (ChatSnapshot in snapshot.children){
                        val name = ChatSnapshot.key.toString() // email cua account
                        chatList.add(name)

                    }
                }
                var listAdapter = ChatListAdapter(this@ChatListActivity,chatList,
                    myEmail.toString()
                )
                ChatListRecyclerview.adapter = listAdapter
            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}