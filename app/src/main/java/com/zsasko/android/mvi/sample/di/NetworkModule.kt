package com.zsasko.android.mvi.sample.di

import android.content.Context
import com.zsasko.android.mvi.sample.BASE_URL
import com.zsasko.android.mvi.sample.api.ApiService
import com.zsasko.android.mvi.sample.data.repository.CommentsRepository
import com.zsasko.android.mvi.sample.data.repository.CommentsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun getRetrofit(): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
            .build()

    @Provides
    fun getApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Named("Dispatcher_IO")
    fun getSchedulerIo(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun getCommentsRepository(
        @ApplicationContext context: Context,
        @Named("Dispatcher_IO") dispatcher: CoroutineDispatcher,
        apiService: ApiService
    ): CommentsRepository = CommentsRepositoryImpl(context, dispatcher, apiService)


}