package com.ansar.techbay.data.network

import com.ansar.techbay.data.db.entities.Comments
import com.ansar.techbay.data.db.entities.Posts
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MyApi {

    @GET("posts")
    suspend fun getPosts() : Response<List<Posts>>

    @GET("posts/{id}/comments")
    suspend fun getComments(@Path("id") id: Int?): Response<List<Comments>>

    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : MyApi{
            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}