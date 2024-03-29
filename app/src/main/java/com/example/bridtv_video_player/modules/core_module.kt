package com.example.bridtv_video_player.modules

//import com.squareup.moshi.Moshi
//import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
//import retrofit2.converter.moshi.MoshiConverterFactory
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val core_module = module {

    //global shared pref
    single<SharedPreferences> {
        androidApplication().getSharedPreferences(
            androidApplication().packageName,
            Context.MODE_PRIVATE
        )
    }

    single { createRetrofit(gson = get(), httpClient = get()) }

    single { createGson() }

    single { createOkHttpClient() }
}

const val token = "ba99d5592ac96b3493c4cd6fa145a8e6"
const val baseUrl = "https://api.vimeo.com"


fun createGson(): Gson {
    return GsonBuilder()
        .create()
}

fun createRetrofit(gson: Gson, httpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient)
        .build()
}


fun createOkHttpClient(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    httpClient.readTimeout(60, TimeUnit.SECONDS)
    httpClient.connectTimeout(60, TimeUnit.SECONDS)
    httpClient.writeTimeout(60, TimeUnit.SECONDS)

    httpClient.addInterceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("Authorization", "Bearer $token")
        val request = requestBuilder.build()
        chain.proceed(request)
    }

    return httpClient.build()
}

inline fun <reified T> create(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}
