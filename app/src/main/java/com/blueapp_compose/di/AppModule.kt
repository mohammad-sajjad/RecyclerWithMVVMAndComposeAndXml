package com.blueapp_compose.di

import android.content.Context
import com.blueapp_compose.ApiDataContract
import com.blueapp_compose.ApiService
import com.blueapp_compose.ImageRepository
import com.blueapp_compose.remote.ApiController
import com.blueapp_compose.remote.NetworkConnectionInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 23/04/25
 * Description: AppModule class.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val CONNECT_TIMEOUT_MINUTES = 2L
    private const val READ_TIMEOUT_MINUTES = 1L
    private const val WRITE_TIMEOUT_MINUTES = 2L

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideNetworkInterceptor(@ApplicationContext context: Context): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        networkInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(networkInterceptor)
        .connectTimeout(CONNECT_TIMEOUT_MINUTES, TimeUnit.MINUTES)
        .readTimeout(READ_TIMEOUT_MINUTES, TimeUnit.MINUTES)
        .writeTimeout(WRITE_TIMEOUT_MINUTES, TimeUnit.MINUTES)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://pixabay.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiController(retrofit: Retrofit): ApiController {
        return retrofit.create(ApiController::class.java)
    }


    @Provides
    @Singleton
    fun provideApiService(apiController: ApiController): ApiService {
        return ApiService(apiController)
    }

    @Provides
    @Singleton
    fun provideApiDataContract(apiService: ApiService): ApiDataContract{
        return apiService
    }

    @Provides
    @Singleton
    fun provideImageRepository(apiService: ApiDataContract): ImageRepository {
        return ImageRepository(apiService)
    }

}