package com.example.winxenchantixshop.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.winxenchantixshop.DTO.User
import com.example.winxenchantixshop.Fragment.CustomerPageFragment
import com.example.winxenchantixshop.Fragment.HomePageFragment
import com.example.winxenchantixshop.Fragment.NotifyPageFragment
import com.example.winxenchantixshop.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var currentFragment : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailUser = intent.getStringExtra("EmailUser").toString()
        User.Email(emailUser)

        //BottomNavigationView + Fragment
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout,HomePageFragment()).commit()
        val bottomNav : BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home ->  {currentFragment = HomePageFragment()}
                R.id.user -> {currentFragment = CustomerPageFragment()}
                R.id.notice -> {currentFragment = NotifyPageFragment()}
            }
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout,currentFragment).commit()
            true
        }

    }
}