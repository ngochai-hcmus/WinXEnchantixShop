package com.example.winxenchantixshop.DTO

class Account
   {
       var email: String? = null
       var type: String? = null

       constructor(){}
       constructor(email:String, type:String){
           this.email = email
           this.type = type

    }
}