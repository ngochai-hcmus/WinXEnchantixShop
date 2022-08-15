package com.example.winxenchantixshop.Activity.AdminSeller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Activity.Util.SwipeToDeleteCallback
import com.example.winxenchantixshop.Adapter.UserInforAdapter
import com.google.firebase.database.*
import com.example.winxenchantixshop.DTO.UserInfor
import com.example.winxenchantixshop.databinding.ActivityListSellerBinding

class ListSellerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListSellerBinding
    private lateinit var db_ref: DatabaseReference
    private lateinit var UserRecyclerview: RecyclerView
    private lateinit var UserAdapter: UserInforAdapter
    private lateinit var UserList: ArrayList<UserInfor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListSellerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        UserRecyclerview = binding.recycleListSeller
        UserRecyclerview.layoutManager = LinearLayoutManager(this)
        UserRecyclerview.setHasFixedSize(true)

        UserList = arrayListOf<UserInfor>()
        UserAdapter = UserInforAdapter(UserList)
        getUserData()

        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos : Int = viewHolder.adapterPosition
                val userToDelete : UserInfor = UserList.get(pos)
                val name = userToDelete.name.toString()
                deleteUser(name)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(UserRecyclerview)

    }

    private fun deleteUser(name: String) {
        UserList = arrayListOf<UserInfor>()
        db_ref = FirebaseDatabase.getInstance().getReference("UserInfor")

        db_ref.child(name).removeValue().addOnSuccessListener {
            //Toast.makeText(applicationContext, "Deleted 1 item", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            //Toast.makeText(applicationContext, "Failed to delete, try again", Toast.LENGTH_LONG).show()
        }

        db_ref = FirebaseDatabase.getInstance().getReference("Account")
        db_ref.child(name).removeValue().addOnSuccessListener {
            Toast.makeText(applicationContext, "Deleted 1 item", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(applicationContext, "Failed to delete, try again", Toast.LENGTH_LONG).show()
        }
    }

    private fun getUserData() {
        UserList = arrayListOf<UserInfor>()
        db_ref = FirebaseDatabase.getInstance().getReference("UserInfor")
        val okQuery = db_ref.orderByChild("type").equalTo("seller")
        okQuery.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                UserList.clear()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(UserInfor::class.java)
                        UserList.add(user!!)

                    }
                    UserRecyclerview.adapter = UserInforAdapter(UserList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}





