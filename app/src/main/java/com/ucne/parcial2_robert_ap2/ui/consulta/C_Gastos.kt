package com.ucne.parcial2_robert_ap2.ui.consulta

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.parcial2_robert_ap2.data.local.entities.GastoEntity
import kotlinx.coroutines.delay

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
                SwipeToDeleteContainer(
                    item = gasto,
                    onDelete = { viewModel.onEvent(C_Gasto_Event.onDelete(gasto)) }
                ){ gasto ->
                    Card(gasto = gasto)
                }
                Spacer(modifier = Modifier.height(8.dp))

            }
        }
    }
}

@Composable
fun Card(
    gasto: GastoEntity
){
    
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
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBackground(
    swipeDismissState: DismissState
){
    val color = if(swipeDismissState.dismissDirection == DismissDirection.EndToStart){
        Color.Red
    }else Color.Transparent
    
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color)
        .padding(16.dp),
        contentAlignment = Alignment.CenterEnd
    ){
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = Color.White
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SwipeToDeleteContainer(
    item: T,
    onDelete: (T) -> Unit,
    animationDuration: Int = 300,
    content: @Composable (T) -> Unit
){
    var isRemoved by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    val state = rememberDismissState(
        confirmValueChange = { value ->
            if(value == DismissValue.DismissedToStart){
                showDialog = true
                false
            }else{
                false
            }
        }
    )
    
    LaunchedEffect(key1 = isRemoved){
        if(isRemoved){
            delay(animationDuration.toLong())
            onDelete(item)
        }
    }
    
    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismiss(
            state = state,
            background = { DeleteBackground(swipeDismissState = state) },
            dismissContent = { content(item) },
            directions = setOf(DismissDirection.EndToStart)
        )

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
                            onDelete(item)
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