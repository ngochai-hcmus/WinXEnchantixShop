package com.example.winxenchantixshop.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.winxenchantixshop.Activity.AdminSeller.EditProfileActivity
import com.example.winxenchantixshop.Activity.Customer.AboutUsActivity
import com.example.winxenchantixshop.Activity.Customer.HelpActivity
import com.example.winxenchantixshop.Activity.LoginRegister.LoginActivity
import com.example.winxenchantixshop.Activity.Product.NewOrderActivity
import com.example.winxenchantixshop.Activity.Product.OrderConfirmActivity
import com.example.winxenchantixshop.Activity.Product.ReviewListActivity
import com.example.winxenchantixshop.Activity.Product.SearchActivity
import com.example.winxenchantixshop.Adapter.NoticeCustomerAdapter
import com.example.winxenchantixshop.DTO.NoticeCustomer
import com.example.winxenchantixshop.DTO.User
import com.example.winxenchantixshop.DTO.UserInfor
import com.example.winxenchantixshop.R
import com.example.winxenchantixshop.databinding.FragmentCustomerPageBinding
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

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
    private lateinit var cardAccount: CardView
    private lateinit var cardHelp: CardView
    private lateinit var cardAboutUs: CardView
    private lateinit var cardLogout: CardView

    private lateinit var txtUserName: TextView
    private lateinit var imageProfile: ImageView
    private lateinit var db_ref : DatabaseReference
    private lateinit var listUser: ArrayList<UserInfor>


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

        val currentUser = User.Email("")
        val Email = currentUser!!.dropLast(10)

        txtUserName = view.findViewById(R.id.txt_username)
        imageProfile = view.findViewById(R.id.img_profile)

        btnConfirm = view.findViewById(R.id.img_confirm)
        btnMyProduct = view.findViewById(R.id.image_ship)
        btnReview = view.findViewById(R.id.image_feedback)
        cardAccount = view.findViewById(R.id.card_account)
        cardHelp = view.findViewById(R.id.card_help)
        cardAboutUs = view.findViewById(R.id.card_about)
        cardLogout = view.findViewById(R.id.card_logout)

        txtUserName.text = Email
        println(currentUser)
        getUser(currentUser)

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
        cardAccount.setOnClickListener{
            val intent = Intent(this@CustomerPageFragment.requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }
        cardHelp.setOnClickListener{
            val intent = Intent(this@CustomerPageFragment.requireContext(), HelpActivity::class.java)
            startActivity(intent)
        }
        cardAboutUs.setOnClickListener{
            val intent = Intent(this@CustomerPageFragment.requireContext(), AboutUsActivity::class.java)
            startActivity(intent)
        }
        cardLogout.setOnClickListener{
            val intent = Intent(this@CustomerPageFragment.requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getUser(name: String) {
        listUser = arrayListOf<UserInfor>()
        db_ref = FirebaseDatabase.getInstance().getReference("UserInfor")
        db_ref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                listUser.clear()
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(UserInfor::class.java)
                        if (user!!.email.toString() == name){
                            listUser.add(user)
                        }
                    }
                    if(listUser[0].image != "") {
                        Picasso.get().load(listUser[0].image).into(imageProfile)
                    }

                }
                else{
//                    Toast.makeText(this@CustomerPageFragment, "Not Existed", Toast.LENGTH_LONG).show()
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }



}