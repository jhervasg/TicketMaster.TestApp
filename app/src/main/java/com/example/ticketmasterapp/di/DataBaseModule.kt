package com.example.ticketmasterapp.di

import android.content.Context
import androidx.room.Room
import com.example.ticketmasterapp.data.local.database.AppDatabase
import com.example.ticketmasterapp.data.local.database.dao.TicketsDao
import com.example.ticketmasterapp.data.remote.TicketApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TicketsDatAa {

    @Provides
    fun provideTicketsDao(appDatabase: AppDatabase): TicketsDao =
        appDatabase.getTicketsDao()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "RssReader"
        ).build()
    }

    @Provides
    fun providePinPlaymentApiService(@Named("tickets") retrofit: Retrofit): TicketApiService {
        return retrofit.create(TicketApiService::class.java)
    }
}