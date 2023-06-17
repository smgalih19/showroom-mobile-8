package id.ac.unpas.showroommobile8.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.ac.unpas.showroommobile8.networks.PromoApi
import id.ac.unpas.showroommobile8.networks.SepedaMotorApi
import id.ac.unpas.showroommobile8.networks.SetoranMobilApi
import id.ac.unpas.showroommobile8.persistences.DataMobilDao
import id.ac.unpas.showroommobile8.persistences.PromoDao
import id.ac.unpas.showroommobile8.persistences.SepedaMotorDao
import id.ac.unpas.showroommobile8.repositories.PromoRepository
import id.ac.unpas.showroommobile8.repositories.SepedaMotorRepository
import id.ac.unpas.showroommobile8.repositories.SetoranMobilRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideSetoranMobilRepository(
        api: SetoranMobilApi,
        dao: DataMobilDao
    ): SetoranMobilRepository{
        return SetoranMobilRepository(api, dao)
    }

    @Provides
    @ViewModelScoped
    fun providePromoRepository(
        api: PromoApi,
        dao: PromoDao
    ): PromoRepository{
        return PromoRepository(api, dao)
    }

    @Provides
    @ViewModelScoped
    fun provideSepedaMotorRepository(
        api: SepedaMotorApi,
        dao: SepedaMotorDao
    ): SepedaMotorRepository{
        return SepedaMotorRepository(api, dao)
    }
}