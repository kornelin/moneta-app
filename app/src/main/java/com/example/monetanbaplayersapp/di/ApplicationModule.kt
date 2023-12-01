package com.example.monetanbaplayersapp.di

import com.example.monetanbaplayersapp.data.api.PlayersApi
import com.example.monetanbaplayersapp.data.api.PlayersService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.balldontlie.io/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePlayersApi(retrofit: Retrofit): PlayersApi {
        return retrofit.create(PlayersApi::class.java)
    }

    @Provides
    @Singleton
    fun providePlayersService(playersApi: PlayersApi): PlayersService {
        return PlayersService(playersApi)
    }
}
