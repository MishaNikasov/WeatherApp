package com.nikasov.weatherapp.di

import android.content.Context
import com.nikasov.weatherapp.utils.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideResourceProvider (@ApplicationContext context: Context) =
        ResourceProvider(context)
}