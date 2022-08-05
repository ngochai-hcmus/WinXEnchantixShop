package com.example.winxenchantixshop.DTO

object User {
    private var userEmail: String? = null

    fun Email(userMail : String): String? {
        if (userEmail == null) {
            userEmail = userMail
        }
        return userEmail
    }
}