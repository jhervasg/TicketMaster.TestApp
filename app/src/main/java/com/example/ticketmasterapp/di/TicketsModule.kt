package com.example.ticketmasterapp.di

import com.example.ticketmasterapp.data.local.TicketsDbRepositoryImpl
import com.example.ticketmasterapp.data.remote.TicketsRepositoryImpl
import com.example.ticketmasterapp.domain.TicketsDbRepository
import com.example.ticketmasterapp.domain.TicketsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TicketsModule {
    @Singleton
    @Binds
    abstract fun bindTicketsRepository(repository: TicketsRepositoryImpl): TicketsRepository

    @Singleton
    @Binds
    abstract fun bindTicketsDbRepository(dbRepository: TicketsDbRepositoryImpl): TicketsDbRepository
}