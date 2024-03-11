package com.ucne.parcial2_robert_ap2.ui.consulta

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.parcial2_robert_ap2.data.local.entities.GastoEntity
import com.ucne.parcial2_robert_ap2.data.repository.GastoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class C_Gasto_ViewModel @Inject constructor(
    private val gastoRepository: GastoRepository
) : ViewModel() {

    private val _state = MutableStateFlow(GastoListState())
    val state = _state.asStateFlow()
    val gastos: Flow<List<GastoEntity>> = gastoRepository.getAll()

    init {
        getGastos()
    }

    fun getGastos(){
        viewModelScope.launch {
            gastoRepository.getAll()
        }
    }

    fun onEvent(event: C_Gasto_Event){
        when(event){
            is C_Gasto_Event.onDelete -> {
                viewModelScope.launch {
                    gastoRepository.deleteGasto(event.gasto)
                }
            }
        }
    }
}

sealed class C_Gasto_Event{
    data class onDelete(val gasto: GastoEntity): C_Gasto_Event()
}