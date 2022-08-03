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
import com.example.winxenchantixshop.Activity.Cart.CartActivity
import com.example.winxenchantixshop.Activity.Product.SearchActivity
import com.example.winxenchantixshop.Adapter.ProductAdapter
import com.example.winxenchantixshop.DTO.Product
import com.example.winxenchantixshop.DTO.ProductView
import com.example.winxenchantixshop.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var viewModel : ProductView
private lateinit var productAdapter : ProductAdapter
private lateinit var productRecyclerView: RecyclerView
private lateinit var listProduct: ArrayList<Product>
private lateinit var btnSearch: FloatingActionButton
private lateinit var btnCart: ImageButton
private lateinit var searchView: EditText


class HomePageFragment : Fragment() {

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

        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productRecyclerView = view.findViewById(R.id.recycleView_list_product)
        productRecyclerView.layoutManager = LinearLayoutManager(context)
        productRecyclerView.setHasFixedSize(true)

        listProduct = arrayListOf<Product>()
        productAdapter = ProductAdapter(listProduct)
        productRecyclerView.adapter = productAdapter

        viewModel = ViewModelProvider(this).get(ProductView::class.java)

        viewModel.allUsers.observe(viewLifecycleOwner, Observer {

            productAdapter.updateProductList(it)
        })

        btnSearch = view.findViewById(R.id.btn_search)
        searchView = view.findViewById(R.id.searching_bar)
        val kw = searchView.text.toString()

        btnSearch.setOnClickListener{
            val intent = Intent(this@HomePageFragment.requireContext(), SearchActivity::class.java)
            intent.putExtra("kw", kw)
            startActivity(intent)
        }

        btnCart = view.findViewById(R.id.btn_shoppingcart)
        btnCart.setOnClickListener{
            val intent = Intent(this@HomePageFragment.requireContext(), CartActivity::class.java)
            intent.putExtra("kw", kw)
            startActivity(intent)
        }

    }



}
