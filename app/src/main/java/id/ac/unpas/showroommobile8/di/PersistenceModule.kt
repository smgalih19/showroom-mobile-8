package id.ac.unpas.showroommobile8.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.ac.unpas.showroommobile8.persistences.AppDatabase
import id.ac.unpas.showroommobile8.persistences.DataMobilDao
import id.ac.unpas.showroommobile8.persistences.PromoDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application) :AppDatabase{
        return Room
            .databaseBuilder(
                application,
                AppDatabase::class.java,
                "pengelolaan-data-mobil"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDataMobilDao(appDatabase: AppDatabase) : DataMobilDao{
        return appDatabase.dataMobilDao()
    }

    @Provides
    @Singleton
    fun providePromoDao(appDatabase: AppDatabase) : PromoDao{
        return appDatabase.promoDao()
    }
}