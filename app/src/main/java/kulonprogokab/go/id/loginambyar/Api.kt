package kulonprogokab.go.id.loginambyar

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @POST("web_service/auth/login")
    fun userLogin(@Body request: RequestBody):Call<LoginResponse>

    @GET("web_service/auth/login")
    fun userLoginGet():Call<LoginResponse>
}