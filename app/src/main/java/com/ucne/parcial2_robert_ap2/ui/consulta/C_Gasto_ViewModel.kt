package com.ucne.parcial2_robert_ap2.ui.consulta

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ucne.parcial2_robert_ap2.data.repository.GastoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    init {
        getGastos()
    }

    fun getGastos(){
        viewModelScope.launch {
            gastoRepository.getAll()
        }
    }
}