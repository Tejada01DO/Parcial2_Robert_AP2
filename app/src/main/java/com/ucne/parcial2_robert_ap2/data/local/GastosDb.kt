package com.ucne.parcial2_robert_ap2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ucne.parcial2_robert_ap2.data.local.dao.GastoDao
import com.ucne.parcial2_robert_ap2.data.local.entities.GastoEntity

@Database(entities = [GastoEntity::class], version = 1, exportSchema = false)
abstract class GastosDb : RoomDatabase() {
    abstract fun gastoDao(): GastoDao
}