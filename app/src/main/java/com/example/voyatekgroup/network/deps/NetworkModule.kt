package com.example.voyatekgroup.network.deps

import android.content.Context
import com.example.voyatekgroup.network.createClient
import com.example.voyatekgroup.network.trip.TripNetworkSource
import com.example.voyatekgroup.network.trip.TripNetworkSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClientEngine(): HttpClientEngine = CIO.create()

    @Provides
    @Singleton
    fun provideHttpClient(
        engine: HttpClientEngine,
        @ApplicationContext applicationContext: Context
    ) =
        createClient(engine)

    @Provides
    @Singleton
    fun provideTripNetworkSource(client: HttpClient): TripNetworkSource =
        TripNetworkSourceImpl(client)
}