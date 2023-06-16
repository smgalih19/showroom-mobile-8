package id.ac.unpas.showroommobile8.networks

import com.skydoves.sandwich.ApiResponse
import id.ac.unpas.showroommobile8.model.DataMobil
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface SetoranMobilApi {
    @GET("api/mobil")
    suspend fun all(): ApiResponse<SetoranMobilGetResponse>

    @GET("api/mobil/{id}")
    suspend fun find(@Path("id") id: String): ApiResponse<SetoranMobilSingleGetResponse>

    @POST("api/mobil")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body item: DataMobil): ApiResponse<SetoranMobilSingleGetResponse>

    @PUT("api/mobil/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") pathId: String,
                       @Body item: DataMobil): ApiResponse<SetoranMobilSingleGetResponse>

    @DELETE("api/mobil/{id}")
    suspend fun delete(@Path("id") id: String): ApiResponse<SetoranMobilSingleGetResponse>
}