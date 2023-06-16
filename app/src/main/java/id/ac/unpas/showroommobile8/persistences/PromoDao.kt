package id.ac.unpas.showroommobile8.persistences

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ac.unpas.showroommobile8.model.Promo

@Dao
interface PromoDao {
    @Query("SELECT * FROM Promo")
    fun loadAll(): LiveData<List<Promo>>

    @Query("SELECT * FROM Promo")
    suspend fun getList(): List<Promo>

    @Query("SELECT * FROM Promo WHERE id = :id")
    suspend fun find(id: String): Promo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Promo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Promo>)

    @Delete
    fun delete(item: Promo)

    @Query("DELETE FROM Promo WHERE id = :id")
    suspend fun delete(id: String)
}