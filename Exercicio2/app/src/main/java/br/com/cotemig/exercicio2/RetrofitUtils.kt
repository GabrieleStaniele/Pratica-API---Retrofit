package br.com.cotemig.exercicio2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUtils {
    companion object {
        fun getRetrofit(path: String): Retrofit {
            return Retrofit.Builder().baseUrl(path).addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}