package com.example.winxenchantixshop.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Activity.Product.CartActivity
import com.example.winxenchantixshop.Activity.Product.SearchActivity
import com.example.winxenchantixshop.Adapter.ItemAdapter
import com.example.winxenchantixshop.Adapter.NoticeCustomerAdapter
import com.example.winxenchantixshop.Adapter.ProductAdapter
import com.example.winxenchantixshop.DTO.*
import com.example.winxenchantixshop.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var productAdapter : NoticeCustomerAdapter
private lateinit var noticeRecyclerView: RecyclerView
private lateinit var listNotice: ArrayList<NoticeCustomer>
private lateinit var db_ref : DatabaseReference



class NotifyPageFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.activity_notify_customer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noticeRecyclerView = view.findViewById(R.id.recycleView_list_product_customer)
        noticeRecyclerView.layoutManager = LinearLayoutManager(context)
        noticeRecyclerView.setHasFixedSize(true)

        listNotice = arrayListOf<NoticeCustomer>()
        productAdapter = NoticeCustomerAdapter(listNotice)
        noticeRecyclerView.adapter = productAdapter

        val currentUser = User.Email("")!!.dropLast(10)
        getNotice(currentUser)
    }

    private fun getNotice(name: String) {
        db_ref = FirebaseDatabase.getInstance().getReference("Notification").child(name)
        db_ref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                listNotice.clear()
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        val notice = i.getValue(NoticeCustomer::class.java)
                        listNotice.add(0,notice!!)

                    }
                    noticeRecyclerView.adapter = NoticeCustomerAdapter(listNotice)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

}