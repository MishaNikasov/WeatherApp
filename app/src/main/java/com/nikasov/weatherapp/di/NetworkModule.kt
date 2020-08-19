package com.nikasov.weatherapp.di

import com.nikasov.weatherapp.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit{

        val token = ""

        val requestInterceptor = Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .header("Bearer", token)
                .build()

            return@Interceptor chain.proceed(request)
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.GOOGLE_PHOTO_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}