package id.ac.unpas.showroommobile8.model

import androidx.room.PrimaryKey

data class SepedaMotor(
    @PrimaryKey val id: String,
    val model: String,
    val warna: String,
    val kapasitas: Int,
    val tanggal_rilis: String,
    val harga: Int
)
