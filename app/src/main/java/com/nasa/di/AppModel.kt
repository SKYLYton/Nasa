package com.nasa.di

import android.content.Context
import androidx.room.Room
import com.nasa.BuildConfig
import com.nasa.api.Api
import com.nasa.api.ApiRepository
import com.nasa.api.ApiRepositoryImpl
import com.nasa.api.RetrofitBuilder
import com.nasa.api.helper.InterceptorApi
import com.nasa.db.room.AppDatabase
import com.nasa.db.room.repository.NoteRepository
import com.nasa.db.room.repository.impl.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author Fedotov Yakov
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModel {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context) = context

    @Provides
    @Singleton
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.DB_FILE_NAME)
            //.addMigrations(*migrationsTen)
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideNoteRepository(appDatabase: AppDatabase): NoteRepository =
        NoteRepositoryImpl(appDatabase.noteDao())

    @Provides
    fun provideOkHttp(): OkHttpClient {
        val client = OkHttpClient().newBuilder()

        client.interceptors().add(Interceptor { chain ->
            var request: Request = chain.request()
            val url: HttpUrl =
                request.url.newBuilder().addQueryParameter("api_key", BuildConfig.NASA_API_KEY).build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        })

        return client.connectTimeout(BuildConfig.TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(BuildConfig.TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(BuildConfig.TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient): Api {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.NASA_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideApiWorker(
        retrofitBuilder: RetrofitBuilder,
        api: Api
    ): ApiRepository {
        return ApiRepositoryImpl(retrofitBuilder, api)
    }

}