package id.ac.unpas.showroommobile8.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unpas.showroommobile8.model.DataMobil
import id.ac.unpas.showroommobile8.repositories.SetoranMobilRepository
import javax.inject.Inject

@HiltViewModel
class PengelolaanDataMobilViewModel @Inject constructor(private val
setoranMobilRepository: SetoranMobilRepository) : ViewModel()
{
    private val _isLoading: MutableLiveData<Boolean> =
MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _success: MutableLiveData<Boolean> = MutableLiveData()
    val success: LiveData<Boolean> get() = _success

    private val _toast: MutableLiveData<String> = MutableLiveData()
    val toast: LiveData<String> get() = _toast

    private val _list: MutableLiveData<List<DataMobil>> = MutableLiveData()
    val list: LiveData<List<DataMobil>> get() = _list

    suspend fun loadItems(){
        _isLoading.postValue(true)
        setoranMobilRepository.loadItems(onSuccess = {
            _isLoading.postValue(false)
            _list.postValue(it)
        }, onError = {list, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _list.postValue(list)
        })
    }

    suspend fun insert(
        merk: String,
        model: String,
        bahan_bakar: String,
        dijual: String,
        deskripsi: String,
    ){
        _isLoading.postValue(true)
        setoranMobilRepository.insert(merk, model, bahan_bakar, dijual,deskripsi,
        onError = { item, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
        }, onSuccess = {
            _isLoading.postValue(false)
            _success.postValue(true)
        })
    }

    suspend fun loadItem(id: String, onSuccess: (DataMobil?) -> Unit) {
        val item = setoranMobilRepository.find(id)
        onSuccess(item)
    }
    suspend fun update(
        id: String,
        merk: String,
        model: String,
        bahan_bakar: String,
        dijual: String,
        deskripsi: String,
    ) {
        _isLoading.postValue(true)
        setoranMobilRepository.update(id, model, merk, bahan_bakar, dijual, deskripsi,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _success.postValue(true)
                _isLoading.postValue(false)
            }
        )
    }

    suspend fun delete(id: String) {
        _isLoading.postValue(true)
        setoranMobilRepository.delete(id, onError = { message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _success.postValue(true)
        }, onSuccess = {
            _toast.postValue("Data Berhasil Dihapus")
            _isLoading.postValue(false)
            _success.postValue(true)
        })
    }
}