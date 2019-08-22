package Builder

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NameServices {

    @GET("api/users?page=2")
    fun getNameList(): Call<NameList>

    @GET("api/users/{id}")
    fun getNameView(@Path("id") id : String): Call<NameView>
}