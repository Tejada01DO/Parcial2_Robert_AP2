package com.ucne.parcial2_robert_ap2.ui.registro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun GastosScreen(
    viewModel: GastoViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val gasto = state.gasto

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Registro de Gastos",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = gasto.fecha,
                onValueChange = { viewModel.onEvent(GastoEvent.FechaChanged(it)) },
                label = { Text(text = "Fecha") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )

            OutlinedTextField(
                value = gasto.suplidor,
                onValueChange = { viewModel.onEvent(GastoEvent.SuplidorChanged(it)) },
                label = { Text(text = "Suplidor") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )

            OutlinedTextField(
                value = gasto.ncf,
                onValueChange = { viewModel.onEvent(GastoEvent.NcfChanged(it)) },
                label = { Text(text = "Ncf") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )

            OutlinedTextField(
                value = gasto.concepto,
                onValueChange = { viewModel.onEvent(GastoEvent.ConceptoChanged(it)) },
                label = { Text(text = "Concepto") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )

            OutlinedTextField(
                value = gasto.descuento.toString(),
                onValueChange = { viewModel.onEvent(GastoEvent.DescuentoChanged(it)) },
                label = { Text(text = "Descuento") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )

            OutlinedTextField(
                value = gasto.itbis.toString(),
                onValueChange = { viewModel.onEvent(GastoEvent.ItbisChanged(it)) },
                label = { Text(text = "Itbis") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )

            OutlinedTextField(
                value = gasto.monto.toString(),
                onValueChange = { viewModel.onEvent(GastoEvent.MontoChanged(it)) },
                label = { Text(text = "Monto") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
        }
    }
}