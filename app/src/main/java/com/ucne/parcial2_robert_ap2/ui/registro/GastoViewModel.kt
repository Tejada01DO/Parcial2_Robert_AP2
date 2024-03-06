package com.ucne.parcial2_robert_ap2.ui.registro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.parcial2_robert_ap2.data.local.entities.GastoEntity
import com.ucne.parcial2_robert_ap2.data.repository.GastoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GastoViewModel @Inject constructor(
    private val gastoRepository: GastoRepository
) : ViewModel() {
    private val _state = MutableStateFlow(GastoState())
    val state = _state.asStateFlow()

    fun Guardar(){
        viewModelScope.launch {
            gastoRepository.saveGasto(state.value.gasto)
        }
    }

    fun onEvent(event: GastoEvent){
        when(event){
            is GastoEvent.FechaChanged -> {
                _state.update {
                    it.copy(gasto = it.gasto.copy(fecha = event.fecha))
                }
            }

            is GastoEvent.SuplidorChanged -> {
                _state.update {
                    it.copy(gasto = it.gasto.copy(suplidor = event.suplidor))
                }
            }

            is GastoEvent.NcfChanged -> {
                _state.update {
                    it.copy(gasto = it.gasto.copy(ncf = event.ncf))
                }
            }

            is GastoEvent.ConceptoChanged -> {
                _state.update {
                    it.copy(gasto = it.gasto.copy(concepto = event.concepto))
                }
            }

            is GastoEvent.DescuentoChanged -> {
                _state.update {
                    it.copy(gasto = it.gasto.copy(descuento = event.descuento.toIntOrNull() ?: 0))
                }
            }

            is GastoEvent.ItbisChanged -> {
                _state.update {
                    it.copy(gasto = it.gasto.copy(itbis = event.itbis.toIntOrNull() ?: 0))
                }
            }

            is GastoEvent.MontoChanged -> {
                _state.update {
                    it.copy(gasto = it.gasto.copy(monto = event.monto.toIntOrNull() ?: 0))
                }
            }

            GastoEvent.onSave -> {
                Guardar()
            }

            GastoEvent.onLimpiar -> {
                _state.update {
                    it.copy(gasto = GastoEntity())
                }
            }
        }
    }
}

data class GastoState(
    val isLoading: Boolean = false,
    val error: String = "",
    val successMessage: String = "",
    val gasto: GastoEntity = GastoEntity()
)

sealed class GastoEvent{
    data class FechaChanged(val fecha: String): GastoEvent()
    data class SuplidorChanged(val suplidor: String): GastoEvent()
    data class NcfChanged(val ncf: String): GastoEvent()
    data class ConceptoChanged(val concepto: String): GastoEvent()
    data class DescuentoChanged(val descuento: String): GastoEvent()
    data class ItbisChanged(val itbis: String): GastoEvent()
    data class MontoChanged(val monto: String): GastoEvent()
    object onSave: GastoEvent()
    object onLimpiar: GastoEvent()
}