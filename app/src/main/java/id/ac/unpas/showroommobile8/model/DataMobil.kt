package id.ac.unpas.showroommobile8.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataMobil(
    @PrimaryKey val merk: String,
    val model: String,
    val bahanBakar: String,
    val dijual: String,
    val deskripsi: String
)
