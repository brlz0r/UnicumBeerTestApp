package ru.kiruxadance.unicumbeertestapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kiruxadance.unicumbeertestapp.beer.data.data_source.BeerService
import ru.kiruxadance.unicumbeertestapp.beer.data.repository.BeerRepositoryImpl
import ru.kiruxadance.unicumbeertestapp.beer.domain.repository.BeerRepository
import ru.kiruxadance.unicumbeertestapp.beer.domain.use_case.BeerUseCases
import ru.kiruxadance.unicumbeertestapp.beer.domain.use_case.GetBeers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesBaseUrl() : String = "https://api.punkapi.com/v2/"

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL : String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.addInterceptor(logging)

        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideBeerService(retrofit : Retrofit) : BeerService = retrofit.create(BeerService::class.java)

    @Provides
    @Singleton
    fun provideBeersRepository(beerService: BeerService) : BeerRepository {
        return BeerRepositoryImpl(beerService)
    }

    @Provides
    @Singleton
    fun provideBeerUseCases(repository: BeerRepository): BeerUseCases {
        return BeerUseCases(
            getBeers = GetBeers(repository)
        )
    }
}