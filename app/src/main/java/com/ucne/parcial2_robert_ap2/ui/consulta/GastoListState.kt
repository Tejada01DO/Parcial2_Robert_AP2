package com.ucne.parcial2_robert_ap2.ui.consulta

import com.ucne.parcial2_robert_ap2.data.local.entities.GastoEntity

data class GastoListState (
    val isLoading: Boolean = false,
    val gastos: List<GastoEntity>? = emptyList(),
    val error: String = "",
    val gasto: GastoEntity? = GastoEntity(
        0,
        "",
        "",
        "",
        "",
        0,
        0,
        0
    ),
    val selectedGasto: GastoEntity? = null
)