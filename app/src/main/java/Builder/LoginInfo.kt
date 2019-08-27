package Builder


import com.google.gson.annotations.SerializedName

data class LoginInfo(
    @SerializedName("token")
    val token: String
)