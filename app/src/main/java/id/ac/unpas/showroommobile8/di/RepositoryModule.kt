package id.ac.unpas.showroommobile8.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.ac.unpas.showroommobile8.networks.SetoranMobilApi
import id.ac.unpas.showroommobile8.persistences.DataMobilDao
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
}