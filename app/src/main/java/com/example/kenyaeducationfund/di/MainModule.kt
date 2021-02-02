package com.example.kenyaeducationfund.di

import com.example.kenyaeducationfund.repositories.AuthRepository
import com.example.kenyaeducationfund.repositories.DefaultAuthRepository
import com.example.kenyaeducationfund.repositories.DefaultMainRepository
import com.example.kenyaeducationfund.repositories.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped


@Module
@InstallIn(ActivityComponent::class)
object MainModule {

    @ActivityScoped
    @Provides
    fun provideMainRepository() = DefaultMainRepository() as MainRepository
}