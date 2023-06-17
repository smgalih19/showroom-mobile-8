package id.ac.unpas.showroommobile8.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.showroommobile8.model.DataMobil

@Dao
interface DataMobilDao {
    @Query("SELECT * FROM DataMobil ORDER BY merk DESC")
    fun loadAll(): LiveData<List<DataMobil>>

    @Query("SELECT * FROM DataMobil ORDER BY merk DESC")
    suspend fun getList(): List<DataMobil>

    @Query("SELECT * FROM DataMobil WHERE id = :id")
    suspend fun find(id: String): DataMobil?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: DataMobil)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<DataMobil>)

    @Delete
    fun delete(item: DataMobil)

    @Query("DELETE FROM DataMobil WHERE id = :id")
    suspend fun delete(id: String)
}