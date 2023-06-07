package id.ac.unpas.showroommobile8.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.showroommobile8.model.DataMobil

@Database(entities = [DataMobil::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun dataMobilDao(): DataMobilDao
}