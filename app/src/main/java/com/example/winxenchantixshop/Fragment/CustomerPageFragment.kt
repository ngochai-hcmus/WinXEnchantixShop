package com.example.winxenchantixshop.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.winxenchantixshop.Activity.Product.NewOrderActivity
import com.example.winxenchantixshop.Activity.Product.OrderConfirmActivity
import com.example.winxenchantixshop.Activity.Product.ReviewListActivity
import com.example.winxenchantixshop.Activity.Product.SearchActivity
import com.example.winxenchantixshop.Adapter.NoticeCustomerAdapter
import com.example.winxenchantixshop.DTO.NoticeCustomer
import com.example.winxenchantixshop.DTO.User
import com.example.winxenchantixshop.R
import com.example.winxenchantixshop.databinding.FragmentCustomerPageBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CustomerPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CustomerPageFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var btnConfirm: ImageView
    private lateinit var btnMyProduct: ImageView
    private lateinit var btnReview: ImageView


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

        return inflater.inflate(R.layout.fragment_customer_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnConfirm = view.findViewById(R.id.img_confirm)
        btnMyProduct = view.findViewById(R.id.image_ship)
        btnReview = view.findViewById(R.id.image_feedback)

        btnConfirm.setOnClickListener{
            val intent = Intent(this@CustomerPageFragment.requireContext(), NewOrderActivity::class.java)
            startActivity(intent)
        }
        btnMyProduct.setOnClickListener{
            val intent = Intent(this@CustomerPageFragment.requireContext(), OrderConfirmActivity::class.java)
            startActivity(intent)
        }
        btnReview.setOnClickListener{
            val intent = Intent(this@CustomerPageFragment.requireContext(), ReviewListActivity::class.java)
            startActivity(intent)
        }

    }



}