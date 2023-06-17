package id.ac.unpas.showroommobile8.persistences

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ac.unpas.showroommobile8.model.SepedaMotor

@Dao
interface SepedaMotorDao {
        @Query("SELECT * FROM SepedaMotor")
        fun loadAll(): LiveData<List<SepedaMotor>>

        @Query("SELECT * FROM SepedaMotor")
        suspend fun getList(): List<SepedaMotor>

        @Query("SELECT * FROM SepedaMotor WHERE id = :id")
        suspend fun find(id: String): SepedaMotor?

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAll(vararg items: SepedaMotor)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAll(items: List<SepedaMotor>)

        @Delete
        fun delete(item: SepedaMotor)

        @Query("DELETE FROM SepedaMotor WHERE id = :id")
        suspend fun delete(id: String)
}