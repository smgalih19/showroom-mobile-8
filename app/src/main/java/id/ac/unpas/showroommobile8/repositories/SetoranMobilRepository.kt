package id.ac.unpas.showroommobile8.repositories

import com.benasher44.uuid.uuid4
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import id.ac.unpas.showroommobile8.model.DataMobil
import id.ac.unpas.showroommobile8.networks.SetoranMobilApi
import id.ac.unpas.showroommobile8.persistences.DataMobilDao
import javax.inject.Inject

class SetoranMobilRepository @Inject constructor(
    private val api: SetoranMobilApi,
    private val dao: DataMobilDao
    ) : Repository {
    suspend fun loadItems(
        onSuccess: (List<DataMobil>) -> Unit,
        onError: (List<DataMobil>, String) -> Unit
    ) {
        val list: List<DataMobil> = dao.getList()
        api.all()
            .suspendOnSuccess {
                data.whatIfNotNull {
                    it.data?.let { list ->
                        dao.insertAll(list)
                        val items: List<DataMobil> = dao.getList()
                        onSuccess(items)
                    }
                }
            }
            .suspendOnError {
                onError(list, message())
            }
            .suspendOnException {
                onError(list, message())
            }
    }

    suspend fun insert(
        merk: String,
        model: String,
        bahan_bakar: String,
        dijual: String,
        deskripsi: String,
        onSuccess: (DataMobil) -> Unit,
        onError: (DataMobil?, String) -> Unit
    ) {
        val id = uuid4().toString()
        val item = DataMobil(id, merk, model, bahan_bakar, dijual, deskripsi)
        dao.insertAll(item)
        api.insert(item)

            .suspendOnSuccess {
                onSuccess(item)
            }
            .suspendOnError {
                onError(item, message())
            }
            .suspendOnException {
                onError(item, message())
            }
    }

    suspend fun update(
        id: String,
        merk: String,
        model: String,
        bahan_bakar: String,
        dijual: String,
        deskripsi: String,
        onSuccess: (DataMobil) -> Unit,
        onError: (DataMobil?, String) -> Unit
    ) {
        val item = DataMobil(id, merk, model, bahan_bakar, dijual, deskripsi)
        dao.insertAll(item)
        api.update(id, item)
            .suspendOnSuccess {
                onSuccess(item)
            }
            .suspendOnError {
                onError(item, message())
            }
            .suspendOnException {
                onError(item, message())
            }
    }

    suspend fun delete(
        id: String, onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        dao.delete(id)
        api.delete(id)
            .suspendOnSuccess {
                data.whatIfNotNull {
                    onSuccess()
                }
            }
            .suspendOnError {
                onError(message())
            }
            .suspendOnException {
                onError(message())
            }
    }
    suspend fun find(id: String): DataMobil? {
        return dao.find(id)
    }
}