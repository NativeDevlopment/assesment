package com.sixtassignment.di.module

import android.content.res.Configuration
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sixtassignment.util.Constants
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
single {
    Retrofit.Builder()
        .client(get())
        .addConverterFactory(getGsonConverterFactory())
        .baseUrl(get<String>())
        .build() as Retrofit
}

    single {
        Constants.baseURL
    }
}

fun getGsonConverterFactory(): GsonConverterFactory {
    return GsonConverterFactory.create(getGson())
}
fun getGson(): Gson {
    val gsonBuilder = GsonBuilder()
    gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    return gsonBuilder.create()
}

