package ipvc.estg.cm.api

import retrofit2.Call
import retrofit2.http.*

interface Endpoints {

    @GET("/api/mapa")
    fun getUsers(): Call<List<Users>>

    @GET("/users/{id}")
    fun getUserById(@Path("id")id:Int):Call<Users>

    @FormUrlEncoded
    @POST("/api/login")
    fun login(@Field("username") username: String?,
              @Field("password") password: String?): Call<OutputPost>
}