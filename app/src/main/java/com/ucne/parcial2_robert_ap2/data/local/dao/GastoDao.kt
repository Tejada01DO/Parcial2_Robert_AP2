package com.ucne.parcial2_robert_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.ucne.parcial2_robert_ap2.data.local.entities.GastoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GastoDao {
    @Upsert
    suspend fun Guardar(gasto: GastoEntity)

    @Query("SELECT * FROM gastos WHERE gastoId=:id LIMIT 1")
    suspend fun ObtenerPorId(id: Int): GastoEntity?

    @Delete
    suspend fun Eliminar(gasto: GastoEntity)

    @Query("SELECT * FROM gastos")
    fun ObtenerTodos(): Flow<List<GastoEntity>>
}