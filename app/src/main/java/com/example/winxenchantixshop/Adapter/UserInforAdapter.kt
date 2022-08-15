package com.example.winxenchantixshop.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.winxenchantixshop.Activity.Product.ProductInformationActivity
import com.example.winxenchantixshop.DTO.UserInfor
import com.example.winxenchantixshop.R

class UserInforAdapter (private val UserInforList: ArrayList<UserInfor>) : RecyclerView.Adapter<UserInforAdapter.UserInforViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserInforViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_customer,
            parent,false
        )
        return UserInforViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserInforViewHolder, position: Int) {
        val currentItem = UserInforList[position]

        holder.Name.text = currentItem.name
        holder.Email.text = currentItem.email
        holder.Phone.text = currentItem.phone

        val image = currentItem.image
        val age = currentItem.age
        val address = currentItem.address

//        holder.itemView.setOnClickListener(object :View.OnClickListener{
//            override fun onClick(v: View?) {
//                val activity = v!!.context as AppCompatActivity
//                val intent = Intent(v.context, UserProfileActivity::class.java)
//
//                intent.putExtra("image", image)
//                intent.putExtra("name", holder.Name.text)
//                intent.putExtra("age", age)
//                intent.putExtra("phone", holder.Phone.text)
//                intent.putExtra("address", address)
//                intent.putExtra("email", holder.Email.text)
//                activity.startActivity(intent)
//            }
//
//        })
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class UserInforViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val Name : TextView = itemView.findViewById(R.id.Name)
        val Email : TextView = itemView.findViewById(R.id.Email)
        val Phone : TextView = itemView.findViewById(R.id.Phone)
    }

}