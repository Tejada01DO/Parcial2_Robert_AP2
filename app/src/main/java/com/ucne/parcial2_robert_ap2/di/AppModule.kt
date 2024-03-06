package com.ucne.parcial2_robert_ap2.di

import android.content.Context
import androidx.room.Room
import com.ucne.parcial2_robert_ap2.data.local.GastosDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppDbContext(@ApplicationContext context: Context) : GastosDb {
        return Room.databaseBuilder(
            context,
            GastosDb::class.java,
            "GastosDb"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideGastoDao(GastoDb: GastosDb) = GastoDb.gastoDao()
}