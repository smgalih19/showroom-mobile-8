package id.ac.unpas.showroommobile8.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SepedaMotor(
    @PrimaryKey val id: String,
    val model: String,
    val warna: String,
    val kapasitas: Int,
    val tanggal_rilis: String,
    val harga: Int
)
