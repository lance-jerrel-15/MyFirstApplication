package Builder

import retrofit2.Call
import retrofit2.http.*

interface NameServices {

    @GET("api/users?page=2")
    fun getNameList(): Call<NameList>

    @GET("api/users/{id}")
    fun getNameView(@Path("id") id : String): Call<NameView>

    @FormUrlEncoded
    @POST("api/login")
    fun getLogin(@Field("email") email: String,
                 @Field("password") password: String): Call<LoginInfo>

}