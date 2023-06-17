package id.ac.unpas.showroommobile8.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.showroommobile8.model.DataMobil
import id.ac.unpas.showroommobile8.model.Promo
import id.ac.unpas.showroommobile8.model.SepedaMotor

@Database(entities = [DataMobil::class, Promo::class, SepedaMotor::class], version = 4)
abstract class AppDatabase : RoomDatabase(){
    abstract fun dataMobilDao(): DataMobilDao
    abstract fun promoDao(): PromoDao

    abstract fun sepedaMotorDao(): SepedaMotorDao
}