package com.nika.leagueoflegandes.di

import android.content.Context
import androidx.room.Room
import com.nika.leagueoflegandes.db.UserDao
import com.nika.leagueoflegandes.db.UserDatabase
import com.nika.leagueoflegandes.repository.Repository
import com.nika.leagueoflegandes.retrofit.LolApi
import com.nika.leagueoflegandes.retrofit.MatchApi
import com.nika.leagueoflegandes.util.Util.Companion.BASE_API
import com.nika.leagueoflegandes.util.Util.Companion.BASE_MATCH_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLolApi(): LolApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_API)

            .build()
            .create(LolApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMatchApi(): MatchApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_MATCH_URL)  // Replace with your match API base URL
            .build()
            .create(MatchApi::class.java)
    }


    @Provides
    @Singleton
    fun provideRepository(lolApi: LolApi, matchApi: MatchApi ,userDao: UserDao): Repository {
        return Repository(lolApi, matchApi ,userDao)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, UserDatabase::class.java, "user.db"
    ).build()


    @Provides
    fun providePlayerDao(database: UserDatabase) = database.userDao()

}