package com.ucne.parcial2_robert_ap2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gastos")
data class GastoEntity(
    @PrimaryKey(autoGenerate = true)
    val gastoId: Int = 0,
    val fecha: String = "",
    val suplidor: String = "",
    val ncf: String = "",
    val concepto: String = "",
    val descuento: Int = 0,
    val itbis: Int = 0,
    val monto: Int = 0
)
