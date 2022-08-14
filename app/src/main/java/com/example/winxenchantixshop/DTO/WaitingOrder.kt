package com.example.winxenchantixshop.DTO

data class ClientInfo(var location: String? = null,
                      var name: String? = null,
                      var phone: String? = null,
                      var Time: String? = null,
                      var UserID: String? = null,
                      var Cost: String? = null
                      )
{

}

data class WaitingOrder(
                        var ClientInfo: ArrayList<ClientInfo> = ArrayList<ClientInfo>(),
                        var List: ArrayList<Product>? = ArrayList<Product>(),

                        )
{

}