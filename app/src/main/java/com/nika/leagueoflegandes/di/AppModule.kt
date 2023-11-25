package com.nika.leagueoflegandes.di

import com.nika.leagueoflegandes.repository.Repository
import com.nika.leagueoflegandes.retrofit.LolApi
import com.nika.leagueoflegandes.util.Util.Companion.SUMMONERINFO_BASE
import com.nika.leagueoflegandes.util.Util.Companion.SUMMONER_BASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLolApi(): LolApi{
        return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(SUMMONER_BASE)
            .build()
            .create(LolApi::class.java)
    }

    @Singleton
    @Provides
    @Named("sumDetail")
    fun provideLolApiSumDet(): LolApi{
        return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(SUMMONERINFO_BASE)
            .build()
            .create(LolApi::class.java)
    }




    @Provides
    @Singleton
    fun provideRepository(lolApi: LolApi,@Named("sumDetail") lolDetApi:LolApi):Repository{
        return Repository(lolApi,lolDetApi)
    }



}