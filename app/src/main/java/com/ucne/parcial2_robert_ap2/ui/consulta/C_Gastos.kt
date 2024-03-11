package com.ucne.parcial2_robert_ap2.ui.consulta

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.parcial2_robert_ap2.data.local.entities.GastoEntity

@Composable
fun C_Gastos(
    viewModel: C_Gasto_ViewModel = hiltViewModel()
) {
    val gastos by viewModel.gastos.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(text = "Consulta de Gastos", modifier = Modifier.align(Alignment.CenterHorizontally), fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

        LazyColumn{
            items(gastos){gasto ->
                Card(gasto = gasto, onDelete = {viewModel.onEvent(C_Gasto_Event.onDelete(gasto))} )
            }
        }
    }
}

@Composable
fun Card(
    gasto: GastoEntity,
    onDelete: () -> Unit
){
    var showDialog by remember { mutableStateOf(false) }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding()
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                            append("ID: ")
                        }
                        withStyle(style = SpanStyle()){
                            append("${gasto.gastoId}")
                        }
                    })
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                            append("Fecha: ")
                        }
                        withStyle(style = SpanStyle()){
                            append("${gasto.fecha}")
                        }
                    })
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                            append("Suplidor: ")
                        }
                        withStyle(style = SpanStyle()){
                            append("${gasto.suplidor}")
                        }
                    })
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                            append("NCF: ")
                        }
                        withStyle(style = SpanStyle()){
                            append("${gasto.ncf}")
                        }
                    })
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                            append("Concepto: ")
                        }
                        withStyle(style = SpanStyle()){
                            append("${gasto.concepto}")
                        }
                    })
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                            append("Descuento: ")
                        }
                        withStyle(style = SpanStyle()){
                            append("${gasto.descuento}")
                        }
                    })
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                            append("Itbis: ")
                        }
                        withStyle(style = SpanStyle()){
                            append("${gasto.itbis}")
                        }
                    })
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                            append("Monto: ")
                        }
                        withStyle(style = SpanStyle()){
                            append("${gasto.monto}")
                        }
                    })
                }
                IconButton(
                    onClick = {
                        showDialog = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar"
                    )
                }
                if(showDialog){
                    AlertDialog(
                        onDismissRequest = {
                            showDialog = false
                        },
                        icon = { Icon(Icons.Default.Warning, contentDescription = null)},
                        text = { Text("¿Está seguro que desea eliminar este gasto?")},

                        confirmButton = {
                            TextButton(
                                onClick = {
                                    onDelete()
                                    showDialog = false
                                }
                            ) {
                                Text(text = "Eliminar")
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    showDialog = false
                                }
                            ) {
                                Text(text = "Cancelar")
                            }
                        }
                    )
                }
            }
        }
    }
}