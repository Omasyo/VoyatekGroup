package com.example.voyatekgroup.data.deps

import com.example.voyatekgroup.data.trip.TripRepository
import com.example.voyatekgroup.data.trip.TripRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindTripRepository(tripRepository: TripRepositoryImpl): TripRepository
}