package Builder


import com.google.gson.annotations.SerializedName

data class NameList(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)

