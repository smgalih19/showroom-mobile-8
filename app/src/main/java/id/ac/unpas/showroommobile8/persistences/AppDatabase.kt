package id.ac.unpas.showroommobile8.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.showroommobile8.model.DataMobil
import id.ac.unpas.showroommobile8.model.Promo

@Database(entities = [DataMobil::class, Promo::class], version = 3)
abstract class AppDatabase : RoomDatabase(){
    abstract fun dataMobilDao(): DataMobilDao
    abstract fun promoDao(): PromoDao
}