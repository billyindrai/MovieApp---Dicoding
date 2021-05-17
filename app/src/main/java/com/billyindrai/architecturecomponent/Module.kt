package com.billyindrai.architecturecomponent

import android.content.Context
import androidx.room.Room
import com.billyindrai.architecturecomponent.database.Database
import com.billyindrai.architecturecomponent.database.DatabaseDAO
import com.billyindrai.architecturecomponent.repository.API
import com.billyindrai.architecturecomponent.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    fun provideDAO(database: Database): DatabaseDAO {
        return database.databaseDAO()
    }

    @Provides
    fun provideRepository(databaseDAO: DatabaseDAO, api: API): AppRepository {
        return AppRepository(databaseDAO, api)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context.applicationContext,
            Database::class.java,
            "movie_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): API {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/")
            .client(client)
            .build()
        return retrofit.create(API::class.java)
    }
}