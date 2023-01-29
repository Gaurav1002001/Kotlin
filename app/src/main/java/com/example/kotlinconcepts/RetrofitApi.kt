package com.example.kotlinconcepts

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApi {

    @GET(value = "/photos")
    suspend fun getAllData() : Response<List<ApiItem>>

    @GET(value = "/photos/{id}")
    suspend fun getParticularData(@Path(value = "id") id: Int) : Response<ApiItem>

    @GET(value = "/photos")
    suspend fun getQueryData(@Query(value = "albumId") albumId: Int) : Response<List<ApiItem>>
}