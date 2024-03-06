package com.ucne.parcial2_robert_ap2.data.repository

import com.ucne.parcial2_robert_ap2.data.local.dao.GastoDao
import com.ucne.parcial2_robert_ap2.data.local.entities.GastoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GastoRepository @Inject constructor(
    private val gastoDao: GastoDao
) {
    suspend fun saveGasto(gasto: GastoEntity){
        gastoDao.Guardar(gasto)
    }

    suspend fun getAll(): Flow<List<GastoEntity>> {
        return gastoDao.ObtenerTodos()
    }
}