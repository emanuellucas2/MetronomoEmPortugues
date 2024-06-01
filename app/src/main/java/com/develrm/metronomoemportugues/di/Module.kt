package com.develrm.metronomoemportugues.di

import android.content.Context
import com.develrm.metronomoemportugues.data.repository.MetronomeRepository
import com.develrm.metronomoemportugues.util.MediaUtil
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.Module

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    fun provideMetronomeRepository(@ApplicationContext context: Context): MetronomeRepository {
        return MetronomeRepository(context)
    }
    @Provides
    fun provideMediaUtil(@ApplicationContext context: Context): MediaUtil {
        return MediaUtil(context)
    }

}