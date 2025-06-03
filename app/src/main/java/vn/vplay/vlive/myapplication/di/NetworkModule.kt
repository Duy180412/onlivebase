package vn.vplay.vlive.myapplication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vn.vplay.vlive.myapplication.BuildConfig
import vn.vplay.vlive.myapplication.data.remote.api.ContentApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

    @Provides
    @Singleton
    fun provideRetrofitContent(client: OkHttpClient): ContentApi {
        return createService(ContentApi::class.java, client, BuildConfig.BASE_URL_ONLIVE)
    }

    private fun <T> createService(
        serviceClass: Class<T>,
        client: OkHttpClient,
        baseUrl: String
    ): T = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(serviceClass)
}