package id.ac.unpas.showroommobile8.repositories

import com.benasher44.uuid.uuid4
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import id.ac.unpas.showroommobile8.model.Promo
import id.ac.unpas.showroommobile8.networks.PromoApi
import id.ac.unpas.showroommobile8.persistences.PromoDao
import javax.inject.Inject

class PromoRepository @Inject constructor(
    private val api: PromoApi,
    private val dao: PromoDao
) : Repository{
    suspend fun loadItems(
        onSuccess: (List<Promo>) -> Unit,
        onError: (List<Promo>, String) -> Unit
    ) {
        val list: List<Promo> = dao.getList()
        api.all()
            .suspendOnSuccess {
                data.whatIfNotNull {
                    it.data?.let { list ->
                        dao.insertAll(list)
                        val items: List<Promo> = dao.getList()
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
        model: String,
        tanggal_awal: String,
        tanggal_akhir: String,
        persentase: Int,
        onSuccess: (Promo) -> Unit,
        onError: (Promo?, String) -> Unit
    ) {
        val id = uuid4().toString()
        val item = Promo(id, model, tanggal_awal, tanggal_akhir, persentase)
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
        model: String,
        tanggal_awal: String,
        tanggal_akhir: String,
        persentase: Int,
        onSuccess: (Promo) -> Unit,
        onError: (Promo?, String) -> Unit
    ) {
        val item = Promo(id, model, tanggal_awal, tanggal_akhir, persentase)
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
        id: String, onSuccess: () -> Unit, onError: (String) -> Unit
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

    suspend fun find(id: String): Promo? {
        return dao.find(id)
    }
}