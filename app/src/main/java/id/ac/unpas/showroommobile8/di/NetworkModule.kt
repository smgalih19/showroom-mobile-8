package id.ac.unpas.showroommobile8.di

import android.content.Context
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.ac.unpas.showroommobile8.networks.PromoApi
import id.ac.unpas.showroommobile8.networks.SepedaMotorApi
import id.ac.unpas.showroommobile8.networks.SetoranMobilApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(
                "https://ppm-api.gusdya.net/"
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory())
            .build()
    }
    @Provides
    @Singleton
    fun provideSetoramMobilApi(retrofit: Retrofit):
            SetoranMobilApi{
        return retrofit.create(SetoranMobilApi::class.java)
    }

    @Provides
    @Singleton
    fun providePromoApi(retrofit: Retrofit):
            PromoApi{
        return retrofit.create(PromoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSepedaMotorApi(retrofit: Retrofit):
            SepedaMotorApi{
        return retrofit.create(SepedaMotorApi::class.java)
    }
}