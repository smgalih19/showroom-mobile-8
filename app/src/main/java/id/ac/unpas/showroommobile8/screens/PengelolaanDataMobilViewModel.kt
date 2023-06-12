package id.ac.unpas.showroommobile8.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unpas.showroommobile8.model.DataMobil
import id.ac.unpas.showroommobile8.persistences.DataMobilDao
import javax.inject.Inject

@HiltViewModel
class PengelolaanDataMobilViewModel @Inject constructor(private val
dataMobilDao: DataMobilDao) : ViewModel(){
    val list : LiveData<List<DataMobil>> = dataMobilDao.loadAll()

    suspend fun insert(id: String,
                       merk: String,
                       model: String,
                       bahanBakar: String,
                       dijual: String,
                       deskripsi: String){
        val item = DataMobil(id, merk, model, bahanBakar, dijual, deskripsi)
        dataMobilDao.insertAll(item)
    }
}