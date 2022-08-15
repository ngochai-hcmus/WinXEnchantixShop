package com.example.winxenchantixshop.Activity.Product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.winxenchantixshop.Activity.MainActivity
import com.example.winxenchantixshop.R
import com.example.winxenchantixshop.databinding.ActivityCategoryBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHouse.setOnClickListener{
            val intent = Intent(this, CategoryDetailActivity::class.java);
            intent.putExtra("category", "HouseCategory")
            startActivity(intent)
        }

        binding.btnFigure.setOnClickListener{
            val intent = Intent(this, CategoryDetailActivity::class.java);
            intent.putExtra("category", "FigureCategory")
            startActivity(intent)
        }

        binding.btnHair.setOnClickListener{
            val intent = Intent(this, CategoryDetailActivity::class.java);
            intent.putExtra("category", "HairCategory")
            startActivity(intent)
        }

        binding.btnDress.setOnClickListener{
            val intent = Intent(this, CategoryDetailActivity::class.java);
            intent.putExtra("category", "DressCategory")
            startActivity(intent)
        }
    }
}