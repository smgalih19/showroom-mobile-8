package id.ac.unpas.showroommobile8.persistences

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ac.unpas.showroommobile8.model.DataMobil

interface DataMobilDao {
    @Query("SELECT * FROM DataMobil")
    fun loadAll(): LiveData<List<DataMobil>>

    @Query("SELECT * FROM DataMobil WHERE id = :id")
    fun find(id: String): DataMobil?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: DataMobil)

    @Delete
    fun delete(item: DataMobil)
}