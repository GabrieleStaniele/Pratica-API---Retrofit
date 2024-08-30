package br.com.cotemig.exercicio2

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface IAnimalService {

    @GET("/animal")
    fun get(): Call<ArrayList<AnimalModel>>

    @GET("/animal/{id}")
    fun getById(@Path("id") id: String): Call<AnimalModel>

    @POST("/animal")
    fun post(@Body animal: AnimalModel) : Call<Any>

    @PUT("/animal/{id}")
    fun put(@Path("id") id: String, @Body animal: AnimalModel): Call<Any>

    @DELETE("/animal/{id}")
    fun delete(@Path("id") id: String): Call<Any>
}