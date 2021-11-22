package com.chalkboard.birthdays.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    private const val DEFAULT_TIMEOUT = 10L

    fun create() = module {
        single {
            get<Retrofit>(
                qualifier = Qualifiers.BASE_RETROFIT,
                parameters = { parametersOf(emptyList<Interceptor>()) }
            )
        }

        single { provideGson() }

        factory { (interceptors: List<Interceptor>) ->
            provideOkHttpClient(interceptors)
        }

        factory(qualifier = Qualifiers.BASE_RETROFIT) { (interceptors: List<Interceptor>) ->
            provideRetrofit(
                baseUrl = get(qualifier = Qualifiers.BASE_URL),
                okHttpClient = get(parameters = { parametersOf(interceptors) }),
                gson = get()
            )
        }
    }

    private fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(baseUrl)
            .build()

    private fun provideOkHttpClient(interceptors: List<Interceptor>): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .apply {
                interceptors.forEach { addInterceptor(it) }
            }
            .addNetworkInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                requestBuilder.header("Content-Type", "application/json")
                chain.proceed(requestBuilder.build())
            }
            .build()

    private fun provideGson(): Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create()
}
