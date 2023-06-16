package id.ac.unpas.showroommobile8.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataMobil(
    @PrimaryKey val id: String,
    val merk: String,
    val model: String,
    val bahan_bakar: String,
    val dijual: String,
    val deskripsi: String
)
