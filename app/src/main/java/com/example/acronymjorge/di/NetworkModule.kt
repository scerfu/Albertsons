package com.example.acronymjorge.di

import com.example.acronymjorge.remote.AcronymRepositoryImpl
import com.example.acronymjorge.remote.Repository
import com.example.acronymjorge.service.AcronymAPI
import com.example.acronymjorge.service.AcronymAPI.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun providesClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun providesRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesAcronymAPI(
        retrofit: Retrofit
    ): AcronymAPI {
        return retrofit.create(AcronymAPI::class.java)
    }

    @Provides
    fun providesRepository(
        acronymAPI: AcronymAPI
    ): Repository {
        return AcronymRepositoryImpl(acronymAPI)
    }
}