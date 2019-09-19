package m.vk.k021_retrofit.manager.retrofit


import m.vk.k021_retrofit.model.LoginModel
import retrofit2.Call
import android.R.string.no
import retrofit2.http.*
import java.util.ArrayList


interface ApiInterface {

    @GET("check_login.php")
    fun checklogin_gethttp(@Query("user") user: String, @Query("pass") pass: String): Call<LoginModel>

    @FormUrlEncoded
    @POST("check_login.php")
    fun checklogin_getpost(@Field("user") user: String, @Field("pass") pass: String): Call<LoginModel>
}