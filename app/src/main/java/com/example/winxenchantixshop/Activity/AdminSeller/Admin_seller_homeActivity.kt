package com.example.winxenchantixshop.Activity.AdminSeller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.winxenchantixshop.Activity.Chat.ChatListActivity
import com.example.winxenchantixshop.Activity.MainActivity
import com.example.winxenchantixshop.Activity.Product.PostProductActivity
import com.example.winxenchantixshop.DTO.User
import com.example.winxenchantixshop.databinding.FragmentHomePageSellerBinding

class Admin_seller_homeActivity : AppCompatActivity() {

    private lateinit var binding: FragmentHomePageSellerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val email = intent.getStringExtra("email")
        val type = intent.getStringExtra("type")

        binding = FragmentHomePageSellerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val emailUser = intent.getStringExtra("EmailUser").toString()
        User.Email(emailUser)

        binding.layoutMyproduct.setOnClickListener {
            val intent = Intent(this, MyProductActivity::class.java);
            startActivity(intent)
        }
        binding.imgBtnMyProduct.setOnClickListener {
            val intent = Intent(this, MyProductActivity::class.java);
            startActivity(intent)
        }
        binding.layoutPostProduct.setOnClickListener {
            val intent = Intent(this, PostProductActivity::class.java);
            startActivity(intent)
        }
        binding.imgBtnPostProduct.setOnClickListener {
            val intent = Intent(this, PostProductActivity::class.java);
            startActivity(intent)
        }
        binding.layoutNewOrder.setOnClickListener {
            val intent = Intent(this, NewOrderActivity::class.java);
            startActivity(intent)
        }
        binding.imgBtnNewOrder.setOnClickListener {
            val intent = Intent(this, NewOrderActivity::class.java);
            startActivity(intent)
        }
        binding.layoutOrderHistory.setOnClickListener {
            val intent = Intent(this, OrderHistoryActivity::class.java);
            startActivity(intent)
        }
        binding.imgBtnOrderHistory.setOnClickListener {
            val intent = Intent(this, OrderHistoryActivity::class.java);
            startActivity(intent)
        }
        binding.layoutListReview.setOnClickListener {

        }
        binding.layoutReplyReview.setOnClickListener {

        }
        binding.layoutListSeller.setOnClickListener {
            if (type.toString() == "admin"){
                Toast.makeText(this, "success 1", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ListSellerActivity::class.java);
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this, "You are not authorized to view this listing ", Toast.LENGTH_SHORT).show()
            }
        }
        binding.imgBtnListSellerr.setOnClickListener {
            if (type.toString() == "admin"){
                Toast.makeText(this, "success 1", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ListSellerActivity::class.java);
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this, "You are not authorized to view this listing ", Toast.LENGTH_SHORT).show()
            }
        }
        binding.imgBtnListCustomer.setOnClickListener {
            if (type.toString() == "admin"){
                Toast.makeText(this, "success 1", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ListCustomerActivity::class.java);
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this, "You are not authorized to view this listing ", Toast.LENGTH_SHORT).show()
            }
        }
        binding.layoutListCustomer.setOnClickListener {
            if (type.toString() == "admin"){
                Toast.makeText(this, "success 1", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ListCustomerActivity::class.java);
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this, "You are not authorized to view this listing ", Toast.LENGTH_SHORT).show()
            }
        }
        binding.imgChat.setOnClickListener {
            val intent = Intent(this, ChatListActivity::class.java);
            intent.putExtra("email",email.toString())
            startActivity(intent)
        }
        binding.imgBtnConvert.setOnClickListener {
            if (type.toString() == "admin"){
                Toast.makeText(this, "success 1", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ConversionActivity::class.java);
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this, "You are not authorized to view this listing ", Toast.LENGTH_SHORT).show()
            }
        }
        binding.imgBtnEditInfo.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java);
            startActivity(intent)
        }
    }
}